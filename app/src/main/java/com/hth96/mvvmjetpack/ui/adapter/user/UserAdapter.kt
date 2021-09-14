package com.hth96.mvvmjetpack.ui.adapter.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.ListAdapter
import com.hth96.mvvmjetpack.databinding.ItemUserBinding
import com.hth96.mvvmjetpack.databinding.ProgressBarBinding
import com.hth96.mvvmjetpack.model.User
import com.hth96.mvvmjetpack.ui.adapter.diffutil.UserDiffCallback
import com.hth96.mvvmjetpack.ui.adapter.item.RecyclerItemEntity
import com.hth96.mvvmjetpack.ui.adapter.item.RecyclerViewItemType
import com.hth96.mvvmjetpack.ui.adapter.viewholder.DataBindingViewHolder
import javax.inject.Inject

class UserAdapter @Inject constructor(
    private val context: FragmentActivity,
    diffCallback: UserDiffCallback) : ListAdapter<RecyclerItemEntity, DataBindingViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            RecyclerViewItemType.LOADING.value -> DataBindingViewHolder(ProgressBarBinding.inflate(layoutInflater, parent, false))
            else -> DataBindingViewHolder(ItemUserBinding.inflate(layoutInflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder, position: Int) {
        if (holder.binding is ItemUserBinding && getItem(position).data is User) {
            holder.binding.viewModel = getItem(position).data as User
            holder.binding.lifecycleOwner = context
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).itemType.value
    }
}
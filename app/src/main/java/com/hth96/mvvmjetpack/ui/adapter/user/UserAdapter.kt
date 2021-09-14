package com.hth96.mvvmjetpack.ui.adapter.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.ListAdapter
import com.hth96.mvvmjetpack.databinding.ItemUserBinding
import com.hth96.mvvmjetpack.model.User
import com.hth96.mvvmjetpack.ui.adapter.diffutil.UserDiffCallback
import com.hth96.mvvmjetpack.ui.adapter.viewholder.DataBindingViewHolder
import javax.inject.Inject

class UserAdapter @Inject constructor(
    private val context: FragmentActivity,
    diffCallback: UserDiffCallback) : ListAdapter<User, DataBindingViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DataBindingViewHolder(ItemUserBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder, position: Int) {
        if (holder.binding is ItemUserBinding) {
            holder.binding.viewModel = getItem(position)
            holder.binding.lifecycleOwner = context
        }
    }
}
package com.hth96.mvvmjetpack.ui.adapter.user

import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hth96.mvvmjetpack.databinding.ItemUserBinding
import com.hth96.mvvmjetpack.databinding.ProgressBarBinding
import com.hth96.mvvmjetpack.model.User
import com.hth96.mvvmjetpack.ui.adapter.diffutil.UserDiffCallback
import com.hth96.mvvmjetpack.ui.adapter.item.RecyclerItemEntity
import com.hth96.mvvmjetpack.ui.adapter.item.RecyclerViewItemType
import com.hth96.mvvmjetpack.ui.adapter.viewholder.DataBindingViewHolder
import javax.inject.Inject

class UserAdapter @Inject constructor (
    private val context: FragmentActivity,
    diffCallback: UserDiffCallback) :
    ListAdapter<RecyclerItemEntity, DataBindingViewHolder>(diffCallback) {

    var currentPage = 1
    private var canLoadMore = true
    var isLoading = false

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

    fun setupRecyclerViewLoadMore(recyclerView: RecyclerView, loadMoreCallback: (UserAdapter) -> Unit) : UserAdapter {
        recyclerView.clearOnScrollListeners()
        recyclerView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
            val totalItemCount = linearLayoutManager.itemCount
            val lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition()
            if (canLoadMore && !isLoading && totalItemCount <= lastVisibleItem + 1) {
                isLoading = true
                Handler(Looper.getMainLooper()).postDelayed({
                    // Mark is not loading
                    isLoading = false
                }, 10000)
                loadMoreCallback(this)
            }
        }
        return this
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).itemType.value
    }
}
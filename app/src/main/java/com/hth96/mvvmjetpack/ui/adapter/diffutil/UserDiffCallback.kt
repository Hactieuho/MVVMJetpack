package com.hth96.mvvmjetpack.ui.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.hth96.mvvmjetpack.ui.adapter.item.RecyclerItemEntity
import javax.inject.Inject

class UserDiffCallback @Inject constructor() : DiffUtil.ItemCallback<RecyclerItemEntity>() {
    override fun areItemsTheSame(oldItem: RecyclerItemEntity, newItem: RecyclerItemEntity): Boolean {
        return oldItem.data == newItem.data
    }
    override fun areContentsTheSame(oldItem: RecyclerItemEntity, newItem: RecyclerItemEntity): Boolean {
        return oldItem == newItem
    }
}

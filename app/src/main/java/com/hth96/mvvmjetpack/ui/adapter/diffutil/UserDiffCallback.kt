package com.hth96.mvvmjetpack.ui.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.hth96.mvvmjetpack.model.User
import javax.inject.Inject

class UserDiffCallback @Inject constructor() : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}

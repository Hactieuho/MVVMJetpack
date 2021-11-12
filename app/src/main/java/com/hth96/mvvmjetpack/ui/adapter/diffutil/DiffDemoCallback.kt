package com.hth96.mvvmjetpack.ui.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.hth96.mvvmjetpack.model.User

class DiffDemoCallback : DiffUtil.ItemCallback<User>() {
    /**
     * Determine if it is the same item
     *
     * @param oldItem New data
     * @param newItem old Data
     * @return
     */
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    /**
     * When it is the same item, judge whether the content has changed.
     *
     * @param oldItem New data
     * @param newItem old Data
     * @return
     */
    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.firstName == newItem.firstName &&
            oldItem.lastName == newItem.lastName &&
            oldItem.email == newItem.email&&
            oldItem.avatar == newItem.avatar
    }

    /**
     * Optional implementation
     * Implement this method if you need to precisely modify the content of a view.
     * If this method is not implemented, or if null is returned, the entire item will be refreshed.
     *
     * @param oldItem Old data
     * @param newItem New data
     * @return Payload info. if return null, the entire item will be refreshed.
     */
    override fun getChangePayload(oldItem: User, newItem: User): Any? {
        return null
    }
}
package com.hth96.mvvmjetpack.ui.adapter.user

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.hth96.mvvmjetpack.R
import com.hth96.mvvmjetpack.databinding.ItemUserBinding
import com.hth96.mvvmjetpack.model.User

class BaseUserAdapter : BaseQuickAdapter<User, BaseDataBindingHolder<ItemUserBinding>>(R.layout.item_user) {
    override fun convert(holder: BaseDataBindingHolder<ItemUserBinding>, item: User) {

    }
}
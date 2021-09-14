package com.hth96.mvvmjetpack.util

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.blankj.utilcode.util.ToastUtils
import com.bumptech.glide.Glide
import com.hth96.mvvmjetpack.R
import com.hth96.mvvmjetpack.model.User
import com.hth96.mvvmjetpack.resource.Resource
import com.hth96.mvvmjetpack.ui.adapter.UserAdapter
import java.util.*

fun<T: Any?> handleError(
    data: MutableLiveData<Resource<T>>,
    errorText: String) {
    ToastUtils.showShort(errorText)
    data.postValue(Resource.Error(errorText, data.value?.data))
}

@BindingAdapter("app:userAvatar")
fun AppCompatImageView.userAvatar(url: String?) {
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()
    Glide.with(context)
        .load(url)
        .placeholder(circularProgressDrawable)
        .into(this)
}

@BindingAdapter("app:userAdapter", "app:userList")
fun RecyclerView.userAdapter(userAdapter: UserAdapter?, userList: List<User>?) {
    if (adapter == null) {
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        layoutManager = linearLayoutManager
        adapter = userAdapter
    }
    userAdapter?.submitList(userList)
}
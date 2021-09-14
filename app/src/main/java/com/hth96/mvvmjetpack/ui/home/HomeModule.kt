package com.hth96.mvvmjetpack.ui.home

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.hth96.mvvmjetpack.model.User
import com.hth96.mvvmjetpack.resource.Resource

@BindingAdapter(value = ["app:onRefresh"], requireAll = false)
fun SwipeRefreshLayout.onRefresh(callback: () -> Unit) {
    setOnRefreshListener { callback() }
}

@BindingAdapter(value = ["app:isRefreshing"], requireAll = false)
fun SwipeRefreshLayout.isRefreshing(resource: Resource<List<User?>?>?) {
    isRefreshing = resource?.isLoading() == true
}
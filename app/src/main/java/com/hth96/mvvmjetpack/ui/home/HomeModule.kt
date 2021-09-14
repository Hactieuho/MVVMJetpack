package com.hth96.mvvmjetpack.ui.home

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.hth96.mvvmjetpack.ui.adapter.user.UserAdapter

@BindingAdapter(value = ["app:onRefresh"], requireAll = false)
fun SwipeRefreshLayout.onRefresh(callback: () -> Unit) {
    setOnRefreshListener { callback() }
}

@BindingAdapter(value = ["app:isRefreshing"], requireAll = false)
fun SwipeRefreshLayout.isRefreshing(userAdapter: UserAdapter?) {
    isRefreshing = userAdapter?.isLoading == true && userAdapter.currentPage == 1
}
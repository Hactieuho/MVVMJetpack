package com.hth96.mvvmjetpack.ui.adapter.user

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.hth96.mvvmjetpack.R
import com.hth96.mvvmjetpack.data.UserRepository
import com.hth96.mvvmjetpack.databinding.ItemUserBinding
import com.hth96.mvvmjetpack.model.User
import com.hth96.mvvmjetpack.resource.Resource
import javax.inject.Inject

class BaseUserAdapter @Inject constructor (
    private val activity: FragmentActivity,
    private val customLoadMoreView: CustomLoadMoreView) :
    BaseQuickAdapter<User, BaseDataBindingHolder<ItemUserBinding>>(R.layout.item_user),
    LoadMoreModule, PageInfo {

    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private var resultCallback: MutableLiveData<Resource<List<User?>?>>? = null

    override fun convert(holder: BaseDataBindingHolder<ItemUserBinding>, item: User) {
        holder.dataBinding?.viewModel = item
        holder.dataBinding?.lifecycleOwner = activity
    }

    fun initLoadMore(swipe: SwipeRefreshLayout,
                     result: MutableLiveData<Resource<List<User?>?>>,
                     callRequest: (page: Int) -> Unit) {
        swipeRefreshLayout = swipe
        resultCallback = result
        loadMoreModule.loadMoreView = customLoadMoreView
        resultCallback?.observe(activity) {
            if (it.isSuccess()) {
                swipeRefreshLayout?.isRefreshing = false
                if (it.message.equals(UserRepository.NO_MORE_USER)) {
                    loadMoreModule.loadMoreEnd()
                } else {
                    loadMoreModule.loadMoreComplete()
                    // Enable load more after refresh
                    loadMoreModule.isEnableLoadMore = true
                    // Load more success, next page
                    nextPage()
                }
            } else if (it.isError()) {
                swipeRefreshLayout?.isRefreshing = false
                loadMoreModule.loadMoreFail()
            }
        }
        swipeRefreshLayout?.isRefreshing = false
        // Set on refresh listener
        swipeRefreshLayout?.setOnRefreshListener {
            refresh(callRequest)
        }
        // Set on load more listener
        loadMoreModule.setOnLoadMoreListener {
            swipeRefreshLayout?.isRefreshing = false
            callRequest(currentPage)
        }
        loadMoreModule.isAutoLoadMore = true
        loadMoreModule.isEnableLoadMoreIfNotFullPage = true
    }

    private fun refresh(callRequest: (page: Int) -> Unit) {
        swipeRefreshLayout?.isRefreshing = true
        // Disable load more when refresh
        loadMoreModule.isEnableLoadMore = false
        resetCurrentPage()
        callRequest(currentPage)
    }

    override var FIRST_PAGE: Int = 1
    override var PAGE_SIZE: Int = 6
    override var currentPage: Int = 1
}

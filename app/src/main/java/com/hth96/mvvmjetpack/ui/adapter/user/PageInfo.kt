package com.hth96.mvvmjetpack.ui.adapter.user

interface PageInfo {
    var FIRST_PAGE: Int
    var PAGE_SIZE: Int
    var currentPage: Int

    fun nextPage() {
        currentPage++
    }

    fun resetCurrentPage() {
        currentPage = FIRST_PAGE
    }

    val isFirstPage: Boolean
        get() = currentPage == FIRST_PAGE
}
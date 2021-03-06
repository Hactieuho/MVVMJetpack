package com.hth96.mvvmjetpack.ui.adapter.pager

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hth96.mvvmjetpack.ui.home.HomeFragment
import javax.inject.Inject

class MainPagerAdapter @Inject constructor (
    fa: FragmentActivity,
    private val homeFragment: HomeFragment
) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 1

    override fun createFragment(position: Int) = homeFragment
}
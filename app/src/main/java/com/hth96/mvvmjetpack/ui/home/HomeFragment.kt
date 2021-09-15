package com.hth96.mvvmjetpack.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hth96.mvvmjetpack.R
import com.hth96.mvvmjetpack.databinding.FragmentHomeBinding
import com.hth96.mvvmjetpack.ui.adapter.user.BaseUserAdapter
import com.hth96.mvvmjetpack.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment @Inject constructor() : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    val viewModel: MainViewModel by activityViewModels()

    @Inject lateinit var userAdapter: BaseUserAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        initUI()
        observeViewModel()
        return binding.root
    }

    private fun initUI() {
        userAdapter.initLoadMore(binding.swRefresh, viewModel.userRepository.getUsersResult) {
            viewModel.fetchUsers(it)
        }
    }

    private fun observeViewModel() {
        binding.viewModel = viewModel
        binding.userAdapter = userAdapter
        binding.lifecycleOwner = this
        viewModel.fetchUsers()
    }
}
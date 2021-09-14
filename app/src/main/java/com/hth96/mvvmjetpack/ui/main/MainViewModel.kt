package com.hth96.mvvmjetpack.ui.main

import androidx.lifecycle.ViewModel
import com.hth96.mvvmjetpack.data.UserRepository
import com.hth96.mvvmjetpack.ui.adapter.UserAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val userRepository: UserRepository,
    val userAdapter: UserAdapter
) : ViewModel() {
    fun fetchUsers() = userRepository.getUsers()
}
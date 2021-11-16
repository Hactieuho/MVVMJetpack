package com.hth96.mvvmjetpack.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hth96.mvvmjetpack.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val userRepository: UserRepository
) : ViewModel() {
    fun fetchUsers(currentPage: Int? = 1) = viewModelScope.launch {
        userRepository.fetchUsers(currentPage)
    }
}
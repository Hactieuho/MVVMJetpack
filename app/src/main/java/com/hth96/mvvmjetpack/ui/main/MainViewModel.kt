package com.hth96.mvvmjetpack.ui.main

import androidx.lifecycle.ViewModel
import com.hth96.mvvmjetpack.data.ReqresRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val reqresRepository: ReqresRepository
) : ViewModel() {
    fun fetchUsers() = reqresRepository.getUsers()
}
package com.hth96.mvvmjetpack.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
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

    val filterText = MutableLiveData("");
    val filteredUserList = Transformations.switchMap(userRepository.getUsersResult) { l ->
        Transformations.map(filterText) { f ->
            l.data?.filter { u ->
                u?.email?.contains(f, true) == true || u?.fullName()?.contains(f, true) == true
            }
        }
    }

    fun fetchUsers(currentPage: Int? = 1) = viewModelScope.launch {
        userRepository.fetchUsers(currentPage)
    }
}
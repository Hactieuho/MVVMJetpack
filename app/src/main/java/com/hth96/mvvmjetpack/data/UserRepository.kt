package com.hth96.mvvmjetpack.data

import androidx.lifecycle.MutableLiveData
import com.hth96.mvvmjetpack.api.UserService
import com.hth96.mvvmjetpack.model.User
import com.hth96.mvvmjetpack.resource.Resource
import com.hth96.mvvmjetpack.util.handleError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userService: UserService
) {

    // Lay danh sach user
    val getUsersResult = MutableLiveData<Resource<List<User?>?>>()

    val job = Job()
    val ioScope = CoroutineScope(Dispatchers.IO + job)

    // Get user list
    fun fetchUsers(currentPage: Int? = 1) {
        getUsersResult.postValue(Resource.Loading("Getting user list", getUsersResult.value?.data))
        ioScope.launch {
            try {
                val result = userService.getUsers(currentPage)
                if (result.isSuccessful && !result.body()?.data.isNullOrEmpty()) {
                    // Luu lai danh sach town city hoac load more
                    if (currentPage != null && currentPage > 1) {
                        val listUserResult = result.body()?.data as ArrayList?
                        (getUsersResult.value?.data as ArrayList? ?: arrayListOf()).addAll(listUserResult?: arrayListOf())
                        getUsersResult.postValue(Resource.Success(getUsersResult.value?.data))
                    } else {
                        getUsersResult.postValue(Resource.Success(result.body()?.data))
                    }
                } else {
                    getUsersResult.postValue(Resource.Success(getUsersResult.value?.data, NO_MORE_USER))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                handleError(getUsersResult, "Getting user error: ${e.message}")
            }
        }
    }
    companion object {
        const val NO_MORE_USER = "No more user!"
    }
}
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

    // Lay danh sach town city
    val getUsersResult = MutableLiveData<Resource<List<User?>?>>()

    val job = Job()
    val ioScope = CoroutineScope(Dispatchers.IO + job)

    // Get user list
    fun getUsers() {
        getUsersResult.postValue(Resource.Loading("Getting user list", getUsersResult.value?.data))
        ioScope.launch {
            try {
                val result = userService.getUsers()
                if (result.isSuccessful && !result.body()?.data.isNullOrEmpty()) {
                    // Luu lai danh sach town city
                    getUsersResult.postValue(Resource.Success(result.body()?.data?.filter { s ->
                        s.fullName().isNotEmpty() && !s.fullName().contentEquals("town_city") }))
                } else {
                    handleError(getUsersResult, "No user founds!")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                handleError(getUsersResult, "Getting user error: ${e.message}")
            }
        }
    }
}
package com.hth96.mvvmjetpack.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.hth96.mvvmjetpack.api.UserService
import com.hth96.mvvmjetpack.model.User
import com.hth96.mvvmjetpack.resource.Resource
import com.hth96.mvvmjetpack.ui.adapter.item.RecyclerViewItemType
import com.hth96.mvvmjetpack.ui.adapter.item.RecyclerItemEntity
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

    // User entity list for display
    val userEntityList = Transformations.map(getUsersResult) { resource ->
        val listUserEntity = resource.data?.map { user -> RecyclerItemEntity(RecyclerViewItemType.ITEM, user) }
            as ArrayList? ?: arrayListOf()
        if (resource.isLoading() && listUserEntity.isNotEmpty()) {
            listUserEntity.add(RecyclerItemEntity(RecyclerViewItemType.LOADING, null))
        }
        listUserEntity
    }

    val job = Job()
    val ioScope = CoroutineScope(Dispatchers.IO + job)

    // Get user list
    fun fetchUsers() {
        getUsersResult.postValue(Resource.Loading("Getting user list", getUsersResult.value?.data))
        ioScope.launch {
            try {
                val result = userService.getUsers()
                if (result.isSuccessful && !result.body()?.data.isNullOrEmpty()) {
                    // Luu lai danh sach town city
                    getUsersResult.postValue(Resource.Success(result.body()?.data))
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
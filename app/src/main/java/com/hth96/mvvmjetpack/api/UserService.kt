package com.hth96.mvvmjetpack.api

import com.hth96.mvvmjetpack.model.UserList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("users")
    suspend fun getUsers(@Query("page") page: Int? = 1) : Response<UserList?>
}
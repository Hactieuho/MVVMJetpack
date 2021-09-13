package com.hth96.mvvmjetpack.model

import com.squareup.moshi.Json

data class User (
    @Json(name = "id")
    var id: Long? = 0,

    @Json(name = "email")
    var email: String? = null,

    @Json(name = "first_name")
    var firstName: String? = null,

    @Json(name = "last_name")
    var lastName: String? = null,

    @Json(name = "avatar")
    var avatar: String? = null
)
package com.hth96.mvvmjetpack.model

import com.squareup.moshi.Json

class UserList (
    @Json(name = "page")
    var page: Int? = 0,

    @Json(name = "per_page")
    var perPage: Int? = 0,

    @Json(name = "total")
    var total: Int? = 0,

    @Json(name = "total_pages")
    var totalPages: Int? = 0,

    @Json(name = "data")
    var data: List<User>? = null
)
package com.example.myapplication.api.dataModels.commits


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Author(
    @Json(name = "avatar_url")
    val avatarUrl: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "login")
    val login: String?,

    @Json(name = "url")
    val url: String?
)
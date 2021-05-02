package com.example.myapplication.api.dataModels.Issues


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Creator(
    @Json(name = "avatar_url")
    val avatarUrl: String?,

    @Json(name = "id")
    val id: Int?,
    @Json(name = "login")
    val login: String?

)
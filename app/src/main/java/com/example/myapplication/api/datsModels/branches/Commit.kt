package com.example.myapplication.api.datsModels.branches


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Commit(
    @Json(name = "sha")
    val sha: String,
    @Json(name = "url")
    val url: String
)
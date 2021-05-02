package com.example.myapplication.api.datsModels.Issues


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PullRequest(
    @Json(name = "diff_url")
    val diffUrl: String,
    @Json(name = "html_url")
    val htmlUrl: String,
    @Json(name = "patch_url")
    val patchUrl: String,
    @Json(name = "url")
    val url: String
)
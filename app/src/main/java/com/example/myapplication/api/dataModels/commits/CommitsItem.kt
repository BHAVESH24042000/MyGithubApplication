package com.example.myapplication.api.dataModels.commits


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommitsItem(
    @Json(name = "author")
    val author: Author?,
    @Json(name = "comments_url")
    val commentsUrl: String?,
    @Json(name = "commit")
    val commit: Commit?

)
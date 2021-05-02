package com.example.myapplication.api.datsModels.commits


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommitsItem(
    @Json(name = "author")
    val author: Author?,
    @Json(name = "comments_url")
    val commentsUrl: String,
    @Json(name = "commit")
    val commit: Commit,

    @Json(name = "html_url")
    val htmlUrl: String,
    @Json(name = "node_id")
    val nodeId: String,

    @Json(name = "sha")
    val sha: String,
    @Json(name = "url")
    val url: String
)
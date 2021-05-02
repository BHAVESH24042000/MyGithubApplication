package com.example.myapplication.api.datsModels.commits


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Commit(
    @Json(name = "author")
    val author: AuthorX?,
    @Json(name = "comment_count")
    val commentCount: Int,

    @Json(name = "message")
    val message: String?


)
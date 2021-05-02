package com.example.myapplication.api.dataModels.Issues


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class IssuesItem(

    @Json(name = "body")
    val body: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "milestone")
    val milestone: Milestone?,
    @Json(name = "title")
    val title: String?

)
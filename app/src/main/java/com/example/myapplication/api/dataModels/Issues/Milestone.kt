package com.example.myapplication.api.dataModels.Issues


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Milestone(

    @Json(name = "creator")
    val creator: Creator?,
    @Json(name = "description")
    val description: String?

)
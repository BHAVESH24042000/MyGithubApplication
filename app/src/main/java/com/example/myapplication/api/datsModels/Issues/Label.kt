package com.example.myapplication.api.datsModels.Issues


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Label(
    @Json(name = "color")
    val color: String,
    @Json(name = "default")
    val default: Boolean,
    @Json(name = "description")
    val description: Any?,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "node_id")
    val nodeId: String,
    @Json(name = "url")
    val url: String
)
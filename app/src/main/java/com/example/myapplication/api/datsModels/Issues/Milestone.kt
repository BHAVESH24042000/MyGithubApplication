package com.example.myapplication.api.datsModels.Issues


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Milestone(
    @Json(name = "closed_at")
    val closedAt: Any?,
    @Json(name = "closed_issues")
    val closedIssues: Int,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "creator")
    val creator: Creator,
    @Json(name = "description")
    val description: String,
    @Json(name = "due_on")
    val dueOn: Any?,
    @Json(name = "html_url")
    val htmlUrl: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "labels_url")
    val labelsUrl: String,
    @Json(name = "node_id")
    val nodeId: String,
    @Json(name = "number")
    val number: Int,
    @Json(name = "open_issues")
    val openIssues: Int,
    @Json(name = "state")
    val state: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "updated_at")
    val updatedAt: String,
    @Json(name = "url")
    val url: String
)
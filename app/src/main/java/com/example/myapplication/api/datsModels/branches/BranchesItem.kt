package com.example.myapplication.api.datsModels.branches


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BranchesItem(
    @Json(name = "commit")
    val commit: Commit,
    @Json(name = "name")
    val name: String,
    @Json(name = "protected")
    val `protected`: Boolean
)
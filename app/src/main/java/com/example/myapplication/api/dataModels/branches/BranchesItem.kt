package com.example.myapplication.api.dataModels.branches


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BranchesItem(

    @Json(name = "name")
    val name: String?,
    @Json(name = "protected")
    val `protected`: Boolean?
)
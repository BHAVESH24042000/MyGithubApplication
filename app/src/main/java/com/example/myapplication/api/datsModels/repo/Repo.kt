package com.example.myapplication.api.datsModels.repo


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Repo(

        @Json(name = "description")
    val description: String?,

        @Json(name = "full_name")
    val fullName: String?,

        @Json(name = "name")
    val name: String?,

        @Json(name = "organization")
    val organization: Organization?,

        @Json(name = "owner")
    val owner: Owner?

)
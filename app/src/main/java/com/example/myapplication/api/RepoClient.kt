package com.example.myapplication.api

import com.example.myapplication.api.services.RepoAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RepoClient {



    val okHttpBuilder = OkHttpClient.Builder()
        //.readTimeout(5, TimeUnit.SECONDS)
        //.connectTimeout(2, TimeUnit.SECONDS)

    val retrofitBuilder = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(MoshiConverterFactory.create())

    val api = retrofitBuilder
        .client(okHttpBuilder.build())
        .build()
        .create(RepoAPI::class.java)

}
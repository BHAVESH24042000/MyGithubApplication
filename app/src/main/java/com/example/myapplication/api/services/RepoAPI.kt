package com.example.myapplication.api.services

import com.example.myapplication.api.datsModels.Issues.IssuesItem
import com.example.myapplication.api.datsModels.branches.BranchesItem
import com.example.myapplication.api.datsModels.commits.CommitsItem
import com.example.myapplication.api.datsModels.repo.Repo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RepoAPI {

    @GET("repos/{owner}/{repo}")
    suspend fun getRepo(
        @Path("owner") owner: String? = null,
        @Path("repo") repo: String? = null

    ): Response<Repo>

    @GET("repos/{owner}/{repo}/branches")
    suspend fun getBranches(
            @Path("owner") owner: String? = null,
            @Path("repo") repo: String? = null
    ):Response<List<BranchesItem>>


    @GET("repos/{owner}/{repo}/commits")
    suspend fun getCommits(
            @Path("owner") owner: String? = null,
            @Path("repo") repo: String? = null,
            @Query("sha")sha:String?=null
    ):Response<List<CommitsItem?>>

    @GET("repos/{owner}/{repo}/issues")
    suspend fun getIssues(
        @Path("owner") owner: String? = null,
        @Path("repo") repo: String? = null,
        @Query("state")state:String?=null
    ):Response<List<IssuesItem?>>

}
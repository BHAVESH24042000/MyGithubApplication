package com.example.myapplication.api

import com.example.myapplication.api.datsModels.Issues.IssuesItem
import com.example.myapplication.api.datsModels.branches.BranchesItem
import com.example.myapplication.api.datsModels.commits.CommitsItem
import com.example.myapplication.api.datsModels.repo.Repo
import com.example.myapplication.api.services.RepoAPI

object repoCall {

    private val apicall: RepoAPI = RepoClient.api

    suspend fun getRepo(owner: String, repoName:String): Repo? {
        val response= apicall.getRepo(owner,repoName)
        return response.body()

    }

    suspend fun getBranches(owner: String?, repoName:String?): List<BranchesItem>? {
        val response= apicall.getBranches(owner,repoName)
        return response.body()

    }

    suspend fun getCommits(owner: String?, repoName:String?, branch:String?): List<CommitsItem?> {
        val response= apicall.getCommits(owner,repoName,branch)
        return response.body()!!
    }

    suspend fun getIssues(owner: String?, repoName:String?, state:String?): List<IssuesItem?> {
        val response= apicall.getIssues(owner,repoName,state)
        return response.body()!!
    }

}
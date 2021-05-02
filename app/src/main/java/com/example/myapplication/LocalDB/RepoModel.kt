package com.example.myapplication.LocalDB

import android.accounts.AuthenticatorDescription
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RepoModel(
        var owner:String,
        var repoName: String,
        var description: String?,
        //var category:String,

        @PrimaryKey(autoGenerate = true)
        var id:Long=0

)
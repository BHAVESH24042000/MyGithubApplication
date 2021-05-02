package com.example.myapplication.LocalDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao         // https://developer.android.com/codelabs/android-room-with-a-view-kotlin#5 //
interface RepoDao {

    @Insert()
    suspend fun insertRepo( repoModel: RepoModel):Long

    @Query("select * from RepoModel")//" ORDER by repoName ASC")
    fun getTask():LiveData<List<RepoModel>>

    @Query("Delete from RepoModel where id=:uid")
   suspend fun deleteRepo(uid:Long?)


}
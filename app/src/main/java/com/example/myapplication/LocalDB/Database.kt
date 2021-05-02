package com.example.myapplication.LocalDB

import androidx.fragment.app.Fragment
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.DB_NAME
import com.example.myapplication.SecondFragment


@Database(entities=[RepoModel::class],version=2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun todoDao(): RepoDao


    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Fragment): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.requireContext(),
                        AppDatabase::class.java,
                        DB_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
package com.shaadi.codechallenge.model.localdb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class],version = 1,exportSchema = true)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getUserDao():UserDao
}



package com.shaadi.codechallenge.model.localdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Insert
    fun addAll(userList: List<User>)

    @Update
    fun update(user:User):Int

}
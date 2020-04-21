package com.shaadi.codechallenge.repository

import androidx.room.Room
import com.shaadi.codechallenge.app.Shaadi
import com.shaadi.codechallenge.model.localdb.AppDatabase
import com.shaadi.codechallenge.model.localdb.User
import com.shaadi.codechallenge.model.localdb.UserResponse
import com.shaadi.codechallenge.model.network.APIProvider

const val DB_NAME = "shaadi"

object DataRepository {
    private var db : AppDatabase =
        Room.databaseBuilder(Shaadi.appContext,AppDatabase::class.java, DB_NAME).build()

    fun getUserDate():List<User>?{

        val localUsers = db.getUserDao().getAll()

        if(localUsers.isNullOrEmpty()){
            val userRequest = APIProvider.api.getUser().execute()
            if(userRequest.isSuccessful){
                val responseBody:UserResponse? = userRequest.body()
                responseBody?.results?.let {
                    db.getUserDao().addAll(it)
                    return it
                }
            }
            return null
        } else{
            return localUsers
        }
    }

    fun updateUser(user: User) : Int {
        return db.getUserDao().update(user)
    }

}
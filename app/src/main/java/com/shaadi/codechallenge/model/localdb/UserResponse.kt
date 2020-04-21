package com.shaadi.codechallenge.model.localdb

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Timezone(val offset: String = "",
                    val description: String = "")

@Entity(tableName = "user")
data class User(val nat: String = "",
                val gender: String = "",
                val phone: String = "",
                @Embedded
                val dob: Dob,
                @Embedded
                val name: Name,
                @Embedded
                val registered: Registered,
                @Embedded
                val location: Location,
                @Embedded
                val id: Id,
                @Embedded
                val login: Login,
                val cell: String = "",
                val email: String = "",
                @Embedded
                val picture: Picture,
                @PrimaryKey(autoGenerate = true)
                val userId:Int,
                var acceptanceStatus:Int = NOT_SET
)


data class Coordinates(val latitude: String = "",
                       val longitude: String = "")


data class Login(val sha256: String = "",
                 val password: String = "",
                 val salt: String = "",
                 val sha1: String = "",
                 val uuid: String = "",
                 val username: String = "",
                 val md5: String = "")


data class Info(val seed: String = "",
                val page: Int = 0,
                val results: Int = 0,
                val version: String = "")


data class Name(val last: String = "",
                val title: String = "",
                val first: String = "")


data class UserResponse(val results: List<User>?,
                        val info: Info
)


data class Dob(val date: String = "",
               val age: Int = 0)


data class Picture(val thumbnail: String = "",
                   val large: String = "",
                   val medium: String = "")


data class Street(@ColumnInfo(name = "streetNumber")val number: Int = 0,
                  @ColumnInfo(name = "streetName")val name: String = "")


data class Id(val name: String = "",
              val value: String? = null)


data class Registered( @ColumnInfo(name = "regDate")val date: String = "",
                      @ColumnInfo(name = "regAge")val age: Int = 0)


data class Location(val country: String = "",
                    val city: String = "",
                    @Embedded
                    val street: Street,
                    @Embedded
                    val timezone: Timezone,
                    val postcode: String = "",
                    @Embedded
                    val coordinates: Coordinates,
                    val state: String = "")

const val ACCEPTED = 1
const val REJECTED = -1
const val NOT_SET = 0



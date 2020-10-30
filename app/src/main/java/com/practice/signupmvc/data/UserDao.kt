package com.practice.signupmvc.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE email =:email")
    fun isDuplicatedEmail(email:String):List<User>

    @Insert
    fun insert(user: User)

    @Query("SELECT * FROM user WHERE email =:email")
    fun getUserByEmail(email: String):User
}
package com.practice.signupmvc.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Int?,
    @ColumnInfo(name = "email") val email: String="",
    @ColumnInfo(name = "pw") val pw: String,
    @ColumnInfo(name = "nick") val nick: String,
    @ColumnInfo(name = "birth") val birth: String,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "isAgreeService") val isAgreeService: Boolean,
    @ColumnInfo(name = "isAgreemarketing") val isAgreemarketing: Boolean
)

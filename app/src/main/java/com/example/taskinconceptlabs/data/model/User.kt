package com.example.taskinconceptlabs.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    val body: String,
    val email: String,

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "_id")
    val id: Int,
    val name: String,
    val postId: Int
){
    var selected: Boolean = false
}
package com.example.taskinconceptlabs.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.taskinconceptlabs.data.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Query("DELETE FROM user WHERE _id = :id")
    suspend fun remove(id: Int)

    @Query("SELECT * FROM user")
    fun getUsers(): LiveData<List<User>>
}
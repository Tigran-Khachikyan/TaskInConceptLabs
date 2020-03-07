package com.example.taskinconceptlabs.data.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.taskinconceptlabs.data.model.User

@androidx.room.Database(
    entities = [User::class],
    version = 1, exportSchema = false
)

abstract class Database : RoomDatabase() {
    abstract fun getUserDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: Database? = null

        operator fun invoke(context: Context): Database {
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Database::class.java,
                    "user_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
package com.example.taskinconceptlabs.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.taskinconceptlabs.data.db.Database
import com.example.taskinconceptlabs.data.network.ApiUser
import com.example.taskinconceptlabs.data.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class Repository private constructor(
    private val context: Context
) : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = IO + job

    companion object {
        @Volatile
        private var INSTANCE: Repository? = null
        private lateinit var job: Job

        fun getInstance(context: Context): Repository? {
            return INSTANCE ?: synchronized(this) {
                job = Job()
                INSTANCE ?: Repository(context)
            }
        }
    }

    //network
    private val usersLiveData = MutableLiveData<List<User>>()

    fun getUsersFromApi(): LiveData<List<User>> {
        launch {
            try {
                val users = ApiUser().getUsersAsync().await()
                usersLiveData.postValue(users)
            }
            catch (ex : Exception){
            }
        }
        return usersLiveData
    }

    //database
    fun getUsersFromDb(): LiveData<List<User>> =
        Database(context).getUserDao().getUsers()

    suspend fun insertUser(user: User) =
        Database(context).getUserDao().insert(user)

    suspend fun removeUser(id: Int) =
        Database(context).getUserDao().remove(id)
}
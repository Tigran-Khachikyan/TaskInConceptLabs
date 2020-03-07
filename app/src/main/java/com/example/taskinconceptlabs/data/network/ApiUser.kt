package com.example.taskinconceptlabs.data.network

import android.content.Context
import com.example.taskinconceptlabs.data.model.User
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://jsonplaceholder.typicode.com/comments/"

interface ApiUser {

    @GET(".")
    fun getUsersAsync(): Deferred<List<User>>


    companion object {
        operator fun invoke(): ApiUser {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(ApiUser::class.java)
        }
    }

}


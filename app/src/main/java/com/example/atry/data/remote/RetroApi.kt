package com.example.atry.data.remote

import com.plcoding.ktorclientandroid.data.remote.dto.PostResponse
import retrofit2.Response
import retrofit2.http.*
import retrofit2.http.GET

import android.R.string.no
import com.example.atry.data.remote.dto.*
import retrofit2.Call


interface RetroApi {
    @GET("/resources/{id}")
    suspend fun getResources(@Path("id") id:Int): List<Resource>

    @POST("/addResource")
    suspend fun addResource(@Body resource: Resource):Response<PostResponse>

    @GET("/getTasksByduration/{id}")
    suspend fun getTask(@Path("id") id:Int?):List<Task>

    @GET("/getTaskByDate/{id}")
    suspend fun getTasksOfDay(@Path("id") id:Int?,@Query("year") year:Int,@Query("month") month:Int,@Query("day") day:Int):List<Task>

    @POST("/addTask/{id}")
    suspend fun addTask(@Path("id") id: Int?,@Body task:Task):Response<PostResponse>

    @POST ("/login")
    suspend fun login(@Body loginData: Login):LoginData

    @POST ("/register")
    suspend fun register(@Body registerData: Register):Response<PostResponse>

    @GET ("/getDaysOfMonth")
    suspend fun getDayOfMonth(@Query("month") month:String):List<DayInfo>


    companion object{
        const val BASE_URL = "https://api-resource-manager.fandogh.cloud"
    }
}
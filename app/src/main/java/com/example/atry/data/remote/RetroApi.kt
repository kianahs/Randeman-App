package com.example.atry.data.remote

import com.example.atry.data.remote.dto.Resource
import com.example.atry.data.remote.dto.Task
import com.plcoding.ktorclientandroid.data.remote.dto.PostResponse
import retrofit2.Response
import retrofit2.http.*
import retrofit2.http.GET

import android.R.string.no
import com.example.atry.data.remote.dto.Login
import com.example.atry.data.remote.dto.Register
import retrofit2.Call


interface RetroApi {
    @GET("/resources")
    suspend fun getResources(): List<Resource>

    @POST("/addResource")
    suspend fun addResource(@Body resource: Resource):Response<PostResponse>

    @GET("/getTasksByduration/{id}")
    suspend fun getTask(@Path("id") id:Int?):List<Task>

    @POST("/addTask/{id}")
    suspend fun addTask(@Path("id") id: Int?,@Body task:Task):Response<PostResponse>

    @POST ("/login")
    suspend fun login(@Body loginData: Login):Response<PostResponse>

    @POST ("/register")
    suspend fun register(@Body registerData: Register):Response<PostResponse>


    companion object{
        const val BASE_URL = "https://api-resource-manager.fandogh.cloud"
    }
}
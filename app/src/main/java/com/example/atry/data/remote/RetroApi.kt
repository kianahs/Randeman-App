package com.example.atry.data.remote

import com.example.atry.data.remote.dto.Resource
import com.plcoding.ktorclientandroid.data.remote.dto.PostResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetroApi {
    @GET("/resources")
    suspend fun getResources(): List<Resource>

    @POST("/addResource")
    suspend fun addResource(@Body resource: Resource):Response<PostResponse>

    companion object{
        const val BASE_URL = "https://api-resource-manager.fandogh.cloud"
    }
}
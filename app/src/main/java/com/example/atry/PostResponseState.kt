package com.example.atry

import com.example.atry.data.remote.dto.Resource
import com.plcoding.ktorclientandroid.data.remote.dto.PostResponse
import retrofit2.Response

data class PostResponseState(
    val isLoading:Boolean = false,
    val statusCode: Response<PostResponse>? = null,
    val error: String = ""
)

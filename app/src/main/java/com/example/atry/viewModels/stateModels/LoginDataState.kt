package com.example.atry.viewModels.stateModels

import com.example.atry.data.remote.dto.LoginData
import com.example.atry.data.remote.dto.Resource
import com.plcoding.ktorclientandroid.data.remote.dto.PostResponse
import retrofit2.Response

data class LoginDataState(
    val isLoading:Boolean = false,
    val loginData: LoginData? = null,
    val error: String = ""
)

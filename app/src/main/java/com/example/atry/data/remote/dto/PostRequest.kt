package com.plcoding.ktorclientandroid.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostRequest(
    val description: String,
    val name: String
)

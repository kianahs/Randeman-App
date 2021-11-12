package com.plcoding.ktorclientandroid.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostResponse(
    val description: String,
    val name: String
)

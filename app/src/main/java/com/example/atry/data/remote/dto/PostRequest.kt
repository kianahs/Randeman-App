package com.plcoding.ktorclientandroid.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostRequest(
    val __v: Int,
    val _id: String,
    val description: String,
    val name: String,
    val unique_id: Int
)

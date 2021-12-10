package com.example.atry.data.remote.dto

data class Task(
    val _id: String,
    val duration: Int,
    val name: String,
    val priority: Int,
    val unique_id: Int
)
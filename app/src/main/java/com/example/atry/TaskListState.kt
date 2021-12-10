package com.example.atry

import com.example.atry.data.remote.dto.Task

data class TaskListState(
    val isLoading:Boolean = false,
    val tasks:List<Task> = emptyList(),
    val error: String = ""
)

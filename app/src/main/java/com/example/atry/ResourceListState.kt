package com.example.atry

import com.example.atry.data.remote.dto.Resource

data class ResourceListState(
    val isLoading:Boolean = false,
    val resources:List<Resource> = emptyList(),
    val error: String = ""
)

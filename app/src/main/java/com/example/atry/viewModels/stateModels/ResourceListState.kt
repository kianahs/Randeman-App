package com.example.atry.viewModels.stateModels

import com.example.atry.data.remote.dto.Resource

data class ResourceListState(
    val isLoading:Boolean = false,
    val resources:List<Resource> = emptyList(),
    val error: String = ""
)

package com.example.atry.viewModels.stateModels

import com.example.atry.data.remote.dto.DayInfo

data class DayInfoListState(
    val isLoading:Boolean = false,
    val days:List<DayInfo> = emptyList(),
    val error: String = ""
)

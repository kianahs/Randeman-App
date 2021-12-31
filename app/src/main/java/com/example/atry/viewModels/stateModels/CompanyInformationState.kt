package com.example.atry.viewModels.stateModels

import com.example.atry.data.remote.dto.CompanyInformation
import com.example.atry.data.remote.dto.Task

data class CompanyInformationState(
    val isLoading:Boolean = false,
    val informations:CompanyInformation? = null,
    val error: String = ""
)

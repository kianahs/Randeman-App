package com.example.atry.viewModels.stateModels

import com.example.atry.data.remote.dto.CompanyInformation
import com.example.atry.data.remote.dto.Task
import com.example.atry.data.remote.dto.accountInfo

data class AccountInfoState(
    val isLoading:Boolean = false,
    val informations:accountInfo? = accountInfo(0,"example@example.com","Not specified","Not specified"),
    val error: String = ""
)

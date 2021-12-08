package com.example.atry

import androidx.compose.runtime.Composable

class Date(val day: String, val dayNum: String){



    fun getDayName(): String{

        return day
    }

    fun getDayNumber(): String{

        return dayNum
    }



}
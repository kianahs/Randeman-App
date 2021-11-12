package com.example.atry

import androidx.compose.runtime.Composable


class cardStructure(val title: String, icon: @Composable() () -> Unit){

    val item_title = title
    val item_icon = icon

    fun get_title(): String{

        return item_title
    }

    fun get_icon(): @Composable() () -> Unit{
        return item_icon
    }


}
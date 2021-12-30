package com.example.atry

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.plcoding.ktorclientandroid.data.remote.PostsService

@ExperimentalMaterialApi
@Composable
fun seasonsScreen(navController: NavController, id:String?) {
    val service = PostsService.create()
    val items = listOf("Winter","Spring","Summer","Autumn").map { " $it" }
    val month1= listOf("January","February","March")
    val month2= listOf("  April","    May","   June")
    val month3= listOf("    July","  August","September")
    val month4= listOf("October","November","December")
    val col = listOf<Long>(0xFF5DA0D1,0xFF669E68,0xFFE0D29D,0xFFCC845E)
    LazyColumn(modifier = Modifier.padding(bottom = 50.dp)) {
        itemsIndexed(items) { index, item ->

            if(index==0)
                seasons(navController,item,month1,col[index],id)
            if(index==1)
                seasons(navController,item,month2,col[index],id)
            if(index==2)
                seasons(navController,item,month3,col[index],id)
            if(index==3)
                seasons(navController,item,month4,col[index],id)
        }

    }
}

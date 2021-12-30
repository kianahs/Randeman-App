package com.example.atry

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.atry.viewModels.ResourcesViewModel
import com.plcoding.ktorclientandroid.data.remote.PostsService
import com.plcoding.ktorclientandroid.data.remote.dto.PostResponse


@ExperimentalMaterialApi
@Composable
fun  resourcesScreen(navController: NavController, featureChoice:String?){
    val viewModel: ResourcesViewModel = hiltViewModel()

    val service = PostsService.create()
    val posts = produceState<List<PostResponse>>(
        initialValue = emptyList(),
        producer = {
            value = service.getPosts()
        }
    )
    CircularProgressBar(isDisplayed = viewModel.state.value.isLoading)
    LazyColumn(modifier = Modifier.padding(bottom = 50.dp)){

        itemsIndexed(viewModel.state.value.resources){index, item ->
            resourceCard(navController = navController,item.unique_id,item.name,item.description, Modifier.fillMaxSize(),{
                Icon(Icons.Filled.Settings,"",tint = Color(0xFF4552B8),modifier = Modifier.size(40.dp))
            })
        }
    }
    Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.End , modifier = Modifier.fillMaxSize()){
        Icon(
            Icons.Filled.AddCircle,"",tint = Color(0xFF4552B8),
            modifier = Modifier
                .size(130.dp)
                .padding(bottom = 50.dp)
                .clickable { navController.navigate(Screen.resourceFormScreen.route) })
    }


}
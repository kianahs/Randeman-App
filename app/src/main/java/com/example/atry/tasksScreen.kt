package com.example.atry

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.atry.viewModels.GetDayViewModel
import com.example.atry.viewModels.GetTaskViewModel


@ExperimentalMaterialApi
@Composable
fun tasksScreen(navController: NavController, id:String?, month:String?){

    val getTaskViewModel: GetTaskViewModel = hiltViewModel()
    val getDayViewModel: GetDayViewModel = hiltViewModel()


    if (id != null && !getTaskViewModel.dataLoaded.value) {
        getTaskViewModel.getTask(id.toInt())
    }


    var count by remember { mutableStateOf(0) }
    val context = LocalContext.current

    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxWidth()) {


        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween){
            Column() {
                Text(
                    buildAnnotatedString {
//                    append("welcome to ")

                        withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, color = Color(0xFF4552B8), fontSize = 40.sp)
                        ) {
                            append(""+getDayViewModel.state.value)
                        }

                    },
                    modifier = Modifier.padding(10.dp)
                )
                Text(
                    buildAnnotatedString {
//                    append("welcome to ")

                        withStyle(style = SpanStyle(fontWeight = FontWeight.Normal, color = Color(
                            0xFFB0B0B3
                        ), fontSize = 10.sp)
                        ) {
                            append("resource_id :$id ")
                        }

                    },
                    modifier = Modifier.padding(start=10.dp,bottom = 5.dp, top=5.dp)
                )
            }

            Text(
                buildAnnotatedString {
//                    append("welcome to ")

                    withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold, color = Color(0xFF4552B8), fontSize = 20.sp)
                    ) {
                        append("$month")
                    }

                },
                modifier = Modifier.padding(10.dp)
            )
        }




        if (month != null) {
            dayCardScroller(viewmodel = getTaskViewModel, getDayViewModel = getDayViewModel, month)
        }

    }
    CircularProgressBar(isDisplayed = getTaskViewModel.state.value.isLoading)


    LazyColumn(modifier = Modifier.padding(top=200.dp, start = 50.dp,end=10.dp, bottom = 50.dp)){

        itemsIndexed(
            getTaskViewModel.state.value.tasks
        ){index, item ->
            Box(){
                Row() {
                    Icon(
                        Icons.Filled.Info,"",tint = Color(0xFF4552B8),
                        modifier = Modifier
                            .size(40.dp)
                            .padding(top = 20.dp)
                    )

                    taskCard(modifier = Modifier,task = item)

                }

            }

        }
    }

    Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.End , modifier = Modifier.fillMaxSize()){
        Icon(
            Icons.Filled.AddCircle,"",tint = Color(0xFF4552B8),
            modifier = Modifier
                .size(130.dp)
                .padding(bottom = 50.dp)
                .clickable { navController.navigate(Screen.taskFormScreen.withArgs(id.toString())) })
    }


}
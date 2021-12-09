package com.example.atry

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel


import java.sql.*
import java.util.*
import com.example.atry.*
import dagger.hilt.android.AndroidEntryPoint
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            Navigation()
//            resourcesScreen(arrayList)


//            loginScreen()
            //          featuresScreen()
//            resourceFrom()
//            accountForm()
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

//@ExperimentalMaterialApi
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//
////    Column() {
////        taskCard( Modifier,Task("refactor",50,500,Resource("CNC","Nothing")))
////        taskCard( Modifier,Task("refactor",50,500,Resource("CNC","Nothing")))
////        taskCard( Modifier,Task("refactor",50,500,Resource("CNC","Nothing")))
////        taskCard( Modifier,Task("refactor",50,500,Resource("CNC","Nothing")))
////
////    }
//
////   resourceFrom()
//}

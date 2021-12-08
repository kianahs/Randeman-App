package com.example.atry

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


import java.sql.*
import java.util.*
import com.example.atry.*


class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

//            dayCardScroller()

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

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
//   dayCardScroller()

    taskCard(modifier = Modifier,task = Task("run",50,500,Resource("CNC","Nothing")))

//    Column() {
//        taskCard( Modifier,Task("refactor",50,500,Resource("CNC","Nothing")))
//        taskCard( Modifier,Task("refactor",50,500,Resource("CNC","Nothing")))
//        taskCard( Modifier,Task("refactor",50,500,Resource("CNC","Nothing")))
//        taskCard( Modifier,Task("refactor",50,500,Resource("CNC","Nothing")))
//
//    }

//   resourceFrom()
}

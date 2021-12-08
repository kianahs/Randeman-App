package com.example.atry

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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


            Navigation()
//            resourcesScreen(arrayList)


//            loginScreen()
            //          featuresScreen()
//            resourceFrom()
//            accountForm()
        }
    }
}


@ExperimentalMaterialApi
@Composable
fun dayCard(  modifier: Modifier = Modifier){

    Card(
        modifier = Modifier
            .padding(10.dp)
            .clickable { },
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color(0xFFE6E5E3)

    ) {
            Column(Modifier.padding(10.dp)) {
                Text(
                    buildAnnotatedString {

                        withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, color = Color.Black, fontSize = 15.sp)
                        ) {
                            append("Wed")
                        }
                    }
                )

                Text(
                    buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Gray, fontSize = 10.sp),


                        ) {
                        append("21")
                    }
                },
                    modifier = Modifier.padding(8.dp))
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
    dayCard(Modifier)


//    Column() {
//        taskCard( Modifier,Task("refactor",50,500,Resource("CNC","Nothing")))
//        taskCard( Modifier,Task("refactor",50,500,Resource("CNC","Nothing")))
//        taskCard( Modifier,Task("refactor",50,500,Resource("CNC","Nothing")))
//        taskCard( Modifier,Task("refactor",50,500,Resource("CNC","Nothing")))
//
//    }

//   resourceFrom()
}

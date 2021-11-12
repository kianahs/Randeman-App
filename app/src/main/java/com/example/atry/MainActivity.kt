package com.example.atry

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


import java.sql.*
import java.util.*
import com.example.atry.*
import kotlin.collections.ArrayList



import com.example.atry.ui.theme.TryTheme


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
@Composable
fun accountForm() {
    val resourceNameState = remember { mutableStateOf(TextFieldValue()) }
    val resourceDescriptionState = remember { mutableStateOf(TextFieldValue()) }
    val shape = RoundedCornerShape(topStart = 80.dp)
    val shape2 = CircleShape
    Column(modifier = Modifier.background(Color(0xFF4552B8)),horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.10f).background(Color(0xFF4552B8))
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.ExtraBold, color = Color(0xFFABA0E7), fontSize = 40.sp
                            )
                        ) {
                            append("Edit Profile")
                        }
                    }
                )
            }
        }
        Box(modifier = Modifier.clip(shape).fillMaxWidth().fillMaxHeight().background(Color.White)
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier =Modifier.size(120.dp).fillMaxHeight(10f).clip(shape2).background(Color.Gray)
                ){

                    Spacer(modifier = Modifier.padding(5.dp))
                    Icon(Icons.Filled.Person,"",tint = Color(0xFFA3ACEE),modifier = Modifier.size(120.dp))

                }

                Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.20f).background(Color.White)

                ){
                    Column(
                        modifier = Modifier.fillMaxWidth().fillMaxHeight(0.4f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.padding(5.dp))
                        Icon(Icons.Filled.AddCircle,"photo",tint = Color(0xFFB9BDDA),modifier = Modifier.size(40.dp))


                    }
                }
                textInput(textFieldName = "Your name", true)
                Spacer(modifier = Modifier.padding(5.dp))
                textInput(textFieldName = "Username",true)
                Spacer(modifier = Modifier.padding(5.dp))
                textInput(textFieldName = "Password", false)
                Spacer(modifier = Modifier.padding(5.dp))
                textInput(textFieldName = "Company name", true)
                Spacer(modifier = Modifier.padding(15.dp))
                Icon(Icons.Outlined.CheckCircle,"",tint = Color(0xFF626CC2),modifier = Modifier.size(40.dp))


            }

        }



    }

}

@Composable
fun resourceFrom() {

    val resourceNameState = remember { mutableStateOf(TextFieldValue()) }
    val resourceDescriptionState = remember { mutableStateOf(TextFieldValue()) }
    val shape = RoundedCornerShape(topStart = 80.dp)

    Column(modifier = Modifier.background(Color(0xFF4552B8))) {

        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.35f)
            .background(Color(0xFF4552B8))
            )


        Box(modifier = Modifier
            .clip(shape)
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
            ){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    buildAnnotatedString {
//                    append("welcome to ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, color = Color(0xFF4552B8), fontSize = 40.sp)
                        ) {
                            append("Add resource!")
                        }
                    }
                )
                Spacer(modifier = Modifier.padding(15.dp))
                textInput(textFieldName = "Resource name",true)
                Spacer(modifier = Modifier.padding(5.dp))
                textInput(textFieldName = "Description", false)
                Spacer(modifier = Modifier.padding(15.dp))
                Icon(Icons.Filled.AddCircle,"",tint = Color(0xFF4552B8),modifier = Modifier.size(40.dp))


            }

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


//   resourceFrom()
}

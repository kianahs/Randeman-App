package com.example.atry

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun contributorForm(navController: NavController) {

    val shape = RoundedCornerShape(topStart = 80.dp)

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier

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
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.ExtraBold, color = Color(0xFFABA0E7), fontSize = 40.sp
                            )
                        ) {
                            append("Add contributor")
                        }
                    }
                )
                Spacer(modifier = Modifier.padding(5.dp))
                textInput(textFieldName = "User Email Address", true)
                Spacer(modifier = Modifier.padding(15.dp))
                Button(modifier = Modifier.size(250.dp,50.dp),shape = RoundedCornerShape(50),colors = ButtonDefaults.buttonColors(backgroundColor = Color(
                    0xFF6C5DBD
                )
                ),onClick = {
                    navController.navigate(Screen.featuresScreen.withArgs("ss"))
                }) {
                    Text(fontWeight = FontWeight.Bold,color = Color.White,text = "Grant Access")

                }


            }

        }



    }

}
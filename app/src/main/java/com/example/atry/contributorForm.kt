package com.example.atry

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.atry.ui.theme.light_purple
import com.example.atry.ui.theme.linearGradientBrush
import com.example.atry.ui.theme.linearGradientBrush_reverse
import com.example.atry.viewModels.AddContributorViewModel


@Composable
fun contributorForm(navController: NavController) {
    var emailState by rememberSaveable { mutableStateOf("") }
    val shape = RoundedCornerShape(topStart = 80.dp)
    val addContributorViewModel:AddContributorViewModel = hiltViewModel()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier

            .fillMaxWidth()
            .fillMaxHeight()
            .background(linearGradientBrush_reverse)
        ){
            Column(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.8f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    Icons.Filled.Person,"",tint = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(100.dp)
                )

                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.ExtraBold, color = Color(0xFFFFFFFF), fontSize = 40.sp
                            )
                        ) {
                            append("Add contributor")
                        }
                    }
                )
                Spacer(modifier = Modifier.padding(15.dp))

                TextField(
                    value = emailState,
                    onValueChange = { emailState = it },
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                    label = { Text("User Email Address") },

                )
                Spacer(modifier = Modifier.padding(15.dp))
                Button(modifier = Modifier.size(250.dp,50.dp),shape = RoundedCornerShape(50),colors = ButtonDefaults.buttonColors(backgroundColor = Color(
                    0xFF6C5DBD
                )
                ),onClick = {

                    addContributorViewModel.addContributor(emailState)
                    navController.navigate(Screen.featuresScreen.withArgs("ss"))
                }) {
                    Text(fontWeight = FontWeight.Bold,color = Color.White,text = "Grant Access")

                }


            }

        }



    }

}
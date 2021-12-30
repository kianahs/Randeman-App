package com.example.atry

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
import com.example.atry.data.remote.dto.Login
import com.example.atry.viewModels.LoginViewModel


@Composable
fun loginScreen(navController: NavController) {
    val loginViewModel: LoginViewModel = hiltViewModel()
    var usernameState by rememberSaveable { mutableStateOf("") }
    var passwordState by rememberSaveable { mutableStateOf("") }
    Box(
        modifier = Modifier
            .background(Color(0xFF6C5DBD))
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

//        Text(
//            buildAnnotatedString {
////                    append("welcome to ")
//                withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, color = Color(0xFF4552B8), fontSize = 50.sp)
//                ) {
//                    append("Welcome !")
//                }
//            }
//        )
//        Spacer(modifier = Modifier.padding(15.dp))
            Box(
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(40.dp))
                    .fillMaxHeight(0.65f)
                    .fillMaxWidth(0.8f)
                    .clip(
                        RoundedCornerShape(40.dp)
                    )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painterResource(R.drawable.logop75), "logo")
                    TextField(
                        value = usernameState,
                        onValueChange = { usernameState = it },
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                        label = {
                            Text("Username")
                        }
                    )
//        textInput(textFieldName = "Username",true)
                    Spacer(modifier = Modifier.padding(5.dp))
                    TextField(
                        value = passwordState,
                        onValueChange = { passwordState = it },
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                        label = { Text("password") },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )
                    Spacer(modifier = Modifier.padding(15.dp))
                    Button(modifier = Modifier.size(250.dp, 50.dp),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(
                                0xFF6C5DBD
                            )
                        ),
                        onClick = {
                            val loginData: Login = Login(usernameState, passwordState)
                            loginViewModel.login(loginData = loginData)
                            navController.navigate(Screen.featuresScreen.withArgs(usernameState))
                        }) {
                        Text(fontWeight = FontWeight.Bold, color = Color.White, text = "Login")

                    }

                }


            }

            Spacer(modifier = Modifier.padding(10.dp))
            Text(
                buildAnnotatedString {
//                    append("welcome to ")
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.ExtraBold, color = Color(
                                0xFFF8F7F7
                            ), fontSize = 12.sp
                        )
                    ) {
                        append("Register")
                    }
                },
                modifier = Modifier.clickable { navController.navigate(Screen.registerScreen.route) }
            )

        }


    }
}
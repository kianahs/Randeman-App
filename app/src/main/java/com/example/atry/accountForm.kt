package com.example.atry

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.atry.data.remote.dto.Login
import com.example.atry.ui.theme.dark_purple
import com.example.atry.ui.theme.linearGradientBrush
import com.example.atry.viewModels.GetAccountInfo


@Composable
fun accountForm(featureChoice: String?,navController: NavController) {

    val firstNameState = remember { mutableStateOf("") }
    val lastNameState = remember { mutableStateOf("") }
    val EmailState = remember { mutableStateOf("") }
    val companyState = remember { mutableStateOf("") }
    val accountInfoViewModel: GetAccountInfo = hiltViewModel()
    accountInfoViewModel.getAccountInfo()
    firstNameState.value = accountInfoViewModel.state.value.informations!!.firstname
    lastNameState.value = accountInfoViewModel.state.value.informations!!.lastname
    companyState.value = accountInfoViewModel.state.value.informations!!.company_id.toString()
    EmailState.value = accountInfoViewModel.state.value.informations!!.email
    val shape = RoundedCornerShape(topStart = 80.dp)
    val shape2 = CircleShape
    Column(modifier = Modifier.background(linearGradientBrush),horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.10f)
            .background(linearGradientBrush)
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
                                fontWeight = FontWeight.ExtraBold, color = Color(0xFFF9F9FC), fontSize = 40.sp
                            )
                        ) {
                            append("Profile")
                        }
                    }
                )
            }
        }
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
                Box(modifier = Modifier
                    .size(120.dp)

                    .clip(shape2)
                    .background(Color.Gray)
                ){

                    Spacer(modifier = Modifier.padding(5.dp))
                    Icon(Icons.Filled.Person,"",tint = Color(0xFFA3ACEE),modifier = Modifier.size(120.dp))

                }

                Box(modifier = Modifier
                    .fillMaxWidth()

                    .background(Color.White)

                ){
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.padding(5.dp))



                    }
                }

                OutlinedTextField(
                    value = firstNameState.value,
                    onValueChange = {
                        firstNameState.value = it
                    },
                    label = { Text("First Name") },
                    enabled = false,
                    singleLine = true
                )

//                textInput(textFieldName = "Your name", true)
                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(
                    value = lastNameState.value,
                    onValueChange = {
                        lastNameState.value = it
                    },
                    label = { Text("Last Name") },
                    enabled = false,
                    singleLine = true
                )

//                textInput(textFieldName = "Username",true)
                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(
                    value = EmailState.value,
                    onValueChange = {
                        EmailState.value = it
                    },
                    label = { Text("Email") },
                    enabled = false,
                    singleLine = true
                )

//                textInput(textFieldName = "Password", false)
                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(
                    value = companyState.value,
                    onValueChange = {
                        companyState.value = it
                    },
                    label = { Text("Company ID") },
                    enabled = false,
                    singleLine = true
                )



                Spacer(modifier = Modifier.padding(5.dp))
                Button(modifier = Modifier.size(175.dp, 40.dp),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFB31A1A)
                    ),
                    onClick = {
                            navController.navigate(Screen.loginScreen.route)
                    }) {
                    Text(fontWeight = FontWeight.Bold, color = Color.White, text = "Log out")

                }
//                Icon(Icons.Outlined.CheckCircle,"",tint = Color(0xFF626CC2),modifier = Modifier.size(40.dp))


            }

        }



    }

}
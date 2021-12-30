package com.example.atry

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun announcementScreen(navController: NavController) {

    var announcementNameState by rememberSaveable{ mutableStateOf("") }
    val shape = RoundedCornerShape( 50.dp)
    val shape2 = CircleShape
    Column(modifier = Modifier.background(Color(0xFF4552B8)),horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
            .background(Color(0xFF4552B8))

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
                            append("Add Announcement")
                        }
                    }
                )
            }
        }
        Box(modifier = Modifier
            .clip(shape)
            .border(BorderStroke(15.dp, color = Color(0xFFFFFFFF)))
            .fillMaxWidth()
            .fillMaxHeight(0.7f)
            .background(Color.White)
        ){
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)

                )
                OutlinedTextField(
                    value = announcementNameState,
                    onValueChange = {
                        announcementNameState = it
                    },
                    label = { Text("Announcement") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.padding(10.dp))

                Icon(
                    Icons.Outlined.AddCircle,"",tint = Color(0xFF626CC2),modifier = Modifier.size(70.dp)
                    .clickable {
                        navController.navigate(Screen.featuresScreen.withArgs("ss"))

                    })

            }

        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(0xFF4552B8))
        )



    }
}

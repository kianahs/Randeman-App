package com.example.atry

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
import androidx.compose.material.icons.filled.AddCircle
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.atry.data.remote.dto.Resource
import com.example.atry.ui.theme.dark_purple
import com.example.atry.ui.theme.light_green
import com.example.atry.ui.theme.linearGradientBrush
import com.example.atry.ui.theme.linearGradientBrush2
import com.example.atry.viewModels.AddResourcesViewModel


@Composable
fun resourceFrom(navController: NavController) {
    val addViewModel: AddResourcesViewModel = hiltViewModel()
    var resourceNameState  by rememberSaveable { mutableStateOf("") }
    var resourceDescriptionState by rememberSaveable { mutableStateOf("") }
    val shape = RoundedCornerShape(topStart = 80.dp)

    Column(modifier = Modifier.background(linearGradientBrush)) {

        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.35f)
            .background(linearGradientBrush)
        )


        Box(modifier = Modifier
            .clip(shape)
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
            .border(6.dp, linearGradientBrush2, shape)
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
                OutlinedTextField(
                    value = resourceNameState,
                    onValueChange = { resourceNameState = it },
                    label = { Text("Enter Resource name") }
                )
                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(
                    value = resourceDescriptionState,
                    onValueChange = { resourceDescriptionState = it },
                    label = { Text("Enter Description") },
                )
                Spacer(modifier = Modifier.padding(15.dp))
                Icon(
                    Icons.Filled.AddCircle,"",tint = Color(0xFF4552B8),
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {

                            val resource: Resource =
                                Resource(
                                    -1,
                                    "null",
                                    resourceDescriptionState,
                                    resourceNameState,
                                    -1,
                                    companyID
                                )
                            addViewModel.addResource(resource)


                            navController.navigate(Screen.resourcesScreen.withArgs("Resources"))
                        })



            }

        }



    }

}
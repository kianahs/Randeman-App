package com.example.atry

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.atry.ui.theme.linearGradientBrush
import com.example.atry.ui.theme.linearGradientBrush_reverse

import com.example.atry.viewModels.GetCompanyInformation

import com.example.atry.viewModels.AddannouncementViewModel



@ExperimentalMaterialApi
@Composable
fun announcementScreen(navController: NavController,
//                       context:Context,
//                       channelId:String,
//                       notificationId:Int
) {

    val context = LocalContext.current
    val channelId = "Randeman"
    val notificationId = 0
    val myBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.logop75)
    val bigText = "this is notification content in randeman app"
    LaunchedEffect(Unit) {
        createNotificationChannel(channelId, context)
    }

    var announcementNameState by rememberSaveable{ mutableStateOf("") }
    val shape = RoundedCornerShape( 50.dp)
    val addannouncementViewModel:AddannouncementViewModel = hiltViewModel()
    val shape2 = CircleShape
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
                    Icons.Filled.Notifications,"",tint = Color(0xFFFFFFFF),
                    modifier = Modifier
                        .size(100.dp)
                        .padding(5.dp)
                )

                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.ExtraBold, color = Color(0xFFFFFFFF), fontSize = 40.sp
                            )
                        ) {
                            append("Add Announcement")
                        }
                    }
                )
                Spacer(modifier = Modifier.padding(15.dp))
                TextField(
                    value = announcementNameState,
                    onValueChange = {
                        announcementNameState = it
                    },
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                    label = { Text("Announcement") },
                    modifier = Modifier.fillMaxWidth(0.7f).fillMaxHeight(0.2f)



                )
                Spacer(modifier = Modifier.padding(15.dp))

                Button(modifier = Modifier.size(250.dp,50.dp),shape = RoundedCornerShape(50),colors = ButtonDefaults.buttonColors(backgroundColor = Color(
                    0xFF6C5DBD
                )
                ),onClick = {
                    addannouncementViewModel.addAnnouncement(announcementNameState)
                    navController.navigate(Screen.featuresScreen.withArgs("ss"))
                    simpleNotificationWithTopAction(
                        context,
                        channelId,
                        notificationId,
                        "New Announcement",
                        "$announcementNameState\n Tap to open"
                    )
                }) {
                    Text(fontWeight = FontWeight.Bold,color = Color.White,text = "Add Announcement")
//                    Text(
//                        text = "simple notification",
//                        fontSize = 16.sp,
//                        modifier = Modifier.padding(5.dp)
//                    )

                }
            }
        }





    }
}

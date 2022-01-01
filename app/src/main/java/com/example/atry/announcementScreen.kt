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
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(0xFF4552B8))
        )



    }
}

package com.example.atry

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun announcementScroller(navController : NavController) {
    val items = listOf("changing plan","repairing CNC","session decisi","calculate fund",
        "changing plan","repairing CNC","session decisi","calculate fund",
        "changing plan","repairing CNC","session decisi","calculate fund",
        "changing plan","repairing CNC","session decisi","calculate fund",)

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(

            buildAnnotatedString {
//                    append("welcome to ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Normal, color = Color(
                    0xFF4552B8
                ), fontSize = 25.sp)
                ) {
                    append("Announcements")
                }
            },
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(4.dp))


        Icon(
            Icons.Filled.Add,"",tint = Color(0xFF4552B8),
            modifier = Modifier.size(40.dp)
                .clickable { navController.navigate(Screen.announcementScreen.route) }

        )

    }
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 60.dp),

        ) {
        itemsIndexed(items) { index, item ->
            Row( modifier= Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                announcement( modifier= Modifier,item)
                var checked by remember {
                    mutableStateOf(false)
                }

                IconToggleButton(
                    checked = checked, onCheckedChange = { checked = it }
                ) {
                    val tint by animateColorAsState(if (checked) Color(0xFFFFC107) else Color(0xFFB0BEC5))
                    Icon(
                        Icons.Filled.Star, contentDescription = "Localized description", tint = tint,
                        modifier = Modifier.size(30.dp))
                }

            }


        }

    }
}
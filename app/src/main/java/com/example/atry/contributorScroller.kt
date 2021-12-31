package com.example.atry

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
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
import com.example.atry.ui.theme.light_green
import com.example.atry.ui.theme.light_purple

@Composable
fun contributorScroller(navController: NavController) {
    val items = listOf("Mina","Erfan","Kiana")
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(

            buildAnnotatedString {
//                    append("welcome to ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, color = Color(0xFF000000), fontSize = 25.sp)
                ) {
                    append("Contributors")
                }
            },
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(4.dp))


        Icon(
            Icons.Filled.Edit,"",tint = light_green,
            modifier = Modifier
                .size(40.dp)
                .clickable { navController.navigate(Screen.contributorScreen.route) }

        )

    }


    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp),

        ) {
        itemsIndexed(items) { index, item ->
            contributor( modifier= Modifier,item)
        }

    }
}
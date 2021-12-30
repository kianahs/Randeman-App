package com.example.atry

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun MyExpandedList(title:String ,content:String ) {
    var expanded by remember { mutableStateOf(false) }

    val rotateState = animateFloatAsState(
        targetValue = if (expanded) 180F else 0F,
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Card(onClick = { expanded = !expanded }, backgroundColor =  Color.White) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(buildAnnotatedString {
//                    append("welcome to ")
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF4552B8),
                            fontSize = 20.sp
                        )
                    ) {
                        append("$title")
                    }
                }, modifier= Modifier.fillMaxWidth(0.9F))

                Icon(
                    Icons.Default.ArrowDropDown, "",
                    modifier = Modifier.rotate(rotateState.value)
                )
            }
        }
        Divider()
        AnimatedVisibility(
            visible = expanded,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF673AB7))
                    .padding(16.dp)
            ) {
                Text(buildAnnotatedString {
//                    append("welcome to ")
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFFFFFFFF),
                            fontSize = 15.sp
                        )
                    ) {
                        append("$content")
                    }
                }, modifier= Modifier.fillMaxWidth(0.9F))
            }
        }
    }
}
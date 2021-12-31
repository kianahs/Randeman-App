package com.example.atry

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun activityCircle(modifier: Modifier, content:String, caption: String){
    Column(modifier = Modifier.padding(5.dp)) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color(0xFFF3F3F3))
                .shadow(elevation = 80.dp).border(2.dp, Color.LightGray, CircleShape),



            ){

            Text(

                buildAnnotatedString {
//                    append("welcome to ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, color = Color(
                        0xFF4552B8
                    ), fontSize = 25.sp)
                    ) {
                        append(content)
                    }
                },
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(4.dp),)
        }
        Text(
            buildAnnotatedString {
//                    append("welcome to ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold, color = Color(0xFF4552B8), fontSize = 15.sp)
                ) {
                    append(caption)
                }
            },
            modifier = Modifier.padding(5.dp),
            textAlign = TextAlign.Center,


            )
    }

}
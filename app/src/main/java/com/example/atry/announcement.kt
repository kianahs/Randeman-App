package com.example.atry

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun announcement(modifier: Modifier = Modifier, name:String){


    Card(
        modifier = Modifier
            .padding(4.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(100.dp),
        backgroundColor = Color(0xFF4552B8)

    ) {
        Row(Modifier.padding(8.dp)) {

            Icon(
                Icons.Filled.Notifications,"",tint = Color(0xFFFFFFFF),
                modifier = Modifier
                    .size(30.dp)
                    .padding(5.dp)
            )
//            Spacer(modifier = Modifier.padding(9.dp))
//            Text("HI")
//            Text(text = "KJCKAHDJ")
            Text(
                buildAnnotatedString {

                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.White, fontSize = 14.sp)
                    ) {
                        append(name)
                    }
                },
                modifier = Modifier.padding(top=5.dp, bottom = 5.dp,end = 100.dp)
            )


        }


    }

}
package com.example.atry

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atry.ui.theme.light_green
import com.example.atry.ui.theme.light_purple


@Composable
fun contributor(modifier: Modifier = Modifier, name:String){
    Card(
        modifier = Modifier
            .padding(8.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color(0xFFF3F3F1),
        border = BorderStroke(2.dp,Color.LightGray)

    ) {
        Row(Modifier.padding(10.dp)) {

            Icon(
                Icons.Filled.Person,"",tint = light_purple,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(100.dp)
            )
            Text(
                buildAnnotatedString {

                    withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold, color = Color.Gray, fontSize = 15.sp)
                    ) {
                        append(name)
                    }
                }
            )


        }


    }

}

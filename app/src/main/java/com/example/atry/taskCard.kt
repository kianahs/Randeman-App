package com.example.atry

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atry.data.remote.dto.Task

@ExperimentalMaterialApi
@Composable
fun taskCard(modifier: Modifier = Modifier, task: Task){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clickable { },
        elevation = 10.dp,
        shape = RoundedCornerShape(20.dp),
        backgroundColor = Color(0xFF673AB7)

    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(Modifier.padding(top=20.dp)) {
                Text(
                    buildAnnotatedString {
//                    append("welcome to ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, color = Color.White, fontSize = 20.sp)
                        ) {
                            append(task.name)
                        }
                    }
                )

                Text(buildAnnotatedString {
//                    append("welcome to ")

                    withStyle(style = SpanStyle(color = Color.White, fontSize = 10.sp),

                        ) {
                        append("Resource:"+"\n")
                        append("Priority:"+task.priority.toString()+"\n")
                        append("Duration:"+task.duration.toString()+"\n")
                        append("started at:"+task.startedAt.toString()+"\n")
                        append("ended at:"+task.endedAt.toString()+"\n")
                    }
                })
                Icon(Icons.Filled.Delete,"",tint = Color(0xFFFFFFFF),modifier = Modifier.size(35.dp).padding(end = 10.dp))

            }
            Column() {
                Image(painter = painterResource(id = R.drawable.task), contentDescription = null ,
                    Modifier.size(80.dp))
            }
        }

    }


}
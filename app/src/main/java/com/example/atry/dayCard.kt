package com.example.atry

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atry.data.remote.dto.DayInfo
import com.example.atry.viewModels.GetDayViewModel
import com.example.atry.viewModels.GetTaskViewModel

@ExperimentalMaterialApi
@Composable
fun dayCard(modifier: Modifier = Modifier, date: DayInfo, viewmodel: GetTaskViewModel, getDayViewModel: GetDayViewModel){
//    var dayCardBackground  by rememberSaveable { mutableStateOf(Color(0xFFF3F3F1)) }
    var colorCondition = if(getDayViewModel.state.value.equals("${date.daysOfWeek.substring(0,3)} ${date.daysOfMonth.toString()}")) Color(
        0xFFDED2F5
    ) else Color(0xFFF3F3F1)
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                viewmodel.deleteTasks()
                getDayViewModel.setDay(date.daysOfWeek.substring(0,3), date.daysOfMonth.toString() )

            },
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp),
        backgroundColor = colorCondition

    ) {
        Column(Modifier.padding(10.dp)) {
            Text(
                buildAnnotatedString {

                    withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold, color = Color.Gray, fontSize = 15.sp)
                    ) {
                        append(date.daysOfWeek.substring(0,3))
                    }
                }
            )

            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold,color = Color.Black, fontSize = 15.sp),


                        ) {
                        append(date.daysOfMonth.toString())
                    }
                },
                modifier = Modifier.padding(start= 8.dp, end= 8.dp))
        }



    }


}
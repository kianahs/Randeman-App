package com.example.atry

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.atry.viewModels.GetDayInfoViewModel
import com.example.atry.viewModels.GetDayViewModel
import com.example.atry.viewModels.GetTaskViewModel

@ExperimentalMaterialApi
@Composable
fun dayCardScroller(
    viewmodel: GetTaskViewModel,
    getDayViewModel: GetDayViewModel,
    month: String,
    id: String?
) {
    val findDaysOfGivenMonth: GetDayInfoViewModel = hiltViewModel()
    findDaysOfGivenMonth.findDaysOfMonth(month)
    val items = findDaysOfGivenMonth.state.value.days

    var monthNumber = when(month){
        "   January" ->  0
        "   February"->  1
        "  March"->  2
        "    April"->  3
        "     May"->  4
        "    June"->  5
        "      July"->  6
        "    August"->  7
        "  September"-> 8
        "  October"-> 9
        "  November"->  10
        "  December"->  11

        else -> 12
    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 100.dp, bottom = 10.dp),

        ) {

        itemsIndexed(items) { index, item ->
            if (id != null) {
                dayCard( modifier= Modifier,item, viewmodel, getDayViewModel,monthNumber, id.toInt())
            }
        }

    }
}
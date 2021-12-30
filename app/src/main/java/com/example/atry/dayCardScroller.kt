package com.example.atry

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.atry.viewModels.GetDayInfoViewModel
import com.example.atry.viewModels.GetDayViewModel
import com.example.atry.viewModels.GetTaskViewModel

@ExperimentalMaterialApi
@Composable
fun dayCardScroller(viewmodel: GetTaskViewModel, getDayViewModel: GetDayViewModel, month: String) {
    val findDaysOfGivenMonth: GetDayInfoViewModel = hiltViewModel()
    findDaysOfGivenMonth.findDaysOfMonth(month)
    val items = findDaysOfGivenMonth.state.value.days

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 100.dp, bottom = 10.dp),

        ) {
        itemsIndexed(items) { index, item ->
            dayCard( modifier= Modifier,item, viewmodel, getDayViewModel)
        }

    }
}
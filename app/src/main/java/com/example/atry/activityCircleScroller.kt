package com.example.atry

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.atry.viewModels.GetCompanyInformation

@Composable
fun activityCircleScroller(taskCount:Int,resourceCount:Int,contributorCount:Int,announcementCount:Int) {

    val getStatistics : GetCompanyInformation = hiltViewModel()
    getStatistics.getCompanyInfo()

    val items = listOf(
        StatisticsStructure("Tasks", taskCount) ,

            StatisticsStructure("Resources",
                resourceCount)
        ,

            StatisticsStructure("Contributors",
                contributorCount)

        ,

            StatisticsStructure("Announcements",
                announcementCount)
        )
//    Log.e("resources", getStatistics.state.value.informations?.resourceCount.toString())
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp),

        ) {
        itemsIndexed(items) { index, item ->
            if (item != null) {
                activityCircle( modifier= Modifier,item.getStatisticsValue().toString(),item.getStatisticsName())
            }
        }

    }
}


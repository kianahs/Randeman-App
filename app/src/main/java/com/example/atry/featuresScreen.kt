package com.example.atry

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.atry.viewModels.GetCompanyInformation


@ExperimentalMaterialApi
@Composable
fun featuresScreen (name:String?, navController: NavController){
    val statistics: GetCompanyInformation = hiltViewModel()
    statistics.getCompanyInfo()
//    LazyColumn() {
//        itemsIndexed(
//            listOf<cardStructure>(
//                cardStructure("Resources", { Icon(Icons.Filled.Add,"",tint = Color(0xFF4552B8),modifier = Modifier.size(40.dp)) }),
//                cardStructure("Tasks", { Icon(Icons.Filled.Add,"",tint = Color(0xFF4552B8),modifier = Modifier.size(40.dp)) }),
//                cardStructure("Setting", { Icon(Icons.Filled.Settings,"",tint = Color(0xFF4552B8),modifier = Modifier.size(40.dp)) }),
//                cardStructure("Account", { Icon(Icons.Filled.Person,"",tint = Color(0xFF4552B8),modifier = Modifier.size(40.dp)) }),
//                cardStructure("FAQ", { Icon(Icons.Filled.Search,"",tint = Color(0xFF4552B8),modifier = Modifier.size(40.dp)) }))
//
//        ) { index, item ->
//            cardElement(item.get_title(), Modifier.fillMaxSize(),item.get_icon(),navController = navController)
//        }
//
//    }

    Column( modifier = Modifier.fillMaxSize()
//        .verticalScroll(rememberScrollState())
    ) {
//        Text(
//            buildAnnotatedString {
//                withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, color = Color(
//                    0xFFFF0000
//                ), fontSize = 12.sp)
//                ) {
//                    append("Company ID : ")
//                    append(companyID.toString())
//                }
//            },
//
//        )

        activityCircleScroller(statistics.state.value.informations!!.taskCount,statistics.state.value.informations!!.resourceCount,statistics.state.value.informations!!.ContributorCount,statistics.state.value.informations!!.announcementCount)
        Spacer(modifier = Modifier.padding(20.dp))
        contributorScroller(navController = navController,statistics.state.value.informations!!.contributors)
        Spacer(modifier = Modifier.padding(20.dp))
        announcementScroller(navController = navController, statistics.state.value.informations!!.announcements)
        Spacer(modifier = Modifier.padding(20.dp))
//        statisticsChips()

    }

}

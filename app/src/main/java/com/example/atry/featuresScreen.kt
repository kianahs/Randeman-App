package com.example.atry

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@ExperimentalMaterialApi
@Composable
fun featuresScreen (name:String?, navController: NavController){

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

        activityCircleScroller()
        Spacer(modifier = Modifier.padding(20.dp))
        contributorScroller(navController = navController)
        Spacer(modifier = Modifier.padding(20.dp))
        announcementScroller(navController = navController)
        Spacer(modifier = Modifier.padding(20.dp))
        statisticsChips()

    }

}

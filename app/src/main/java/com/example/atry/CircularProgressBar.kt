package com.example.atry

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CircularProgressBar(
    isDisplayed:Boolean
){
    if(isDisplayed){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize() ){
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(100.dp),horizontalArrangement = Arrangement.Center){
                CircularProgressIndicator(
                    color = MaterialTheme.colors.primary
                )

            }

        }

    }
}
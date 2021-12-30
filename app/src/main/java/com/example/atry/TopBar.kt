package com.example.atry

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = "Resource Manager", fontSize = 18.sp) },
        backgroundColor = colorResource(id = R.color.purple_500),
        contentColor = Color.White
    )
}
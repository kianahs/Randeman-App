package com.example.atry

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@ExperimentalMaterialApi
@Composable
fun seasons(navController: NavController, name :String, d : List<String>, col : Color, id:String?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
        ,
        elevation = 10.dp,
        shape = RoundedCornerShape(15.dp),
        backgroundColor = col


    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, color = Color(
                            0xFFFFFFFF
                        ), fontSize = 30.sp)
                        ){append("             "+name) }


                    }
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Box(modifier = Modifier) {
                BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                    // LazyRow to display your items horizontally
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        state = rememberLazyListState()
                    ) {
                        itemsIndexed(d) { index, item ->
                            Card(
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(123.dp) // here is the trick
                                    .padding(3.dp)
                                    .clickable {
                                        navController.navigate(
                                            Screen.tasksScreen.withArgs(
                                                id.toString(), "  " + d.get(index)
                                            )
                                        )
                                    }
                            ) {
                                Text("  "+d.get(index),color = col,fontFamily = FontFamily.Serif,fontSize = 18.sp,fontWeight = FontWeight.ExtraBold) // card's content


                            }
                        }

                    }
                }
            }
        }


    }
}
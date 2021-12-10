package com.example.atry

import android.app.DatePickerDialog
import android.content.Context
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController


import java.sql.*
import java.util.*
import com.example.atry.*
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.descriptors.PrimitiveKind
import org.intellij.lang.annotations.JdkConstants
import java.util.Date
import com.example.atry.Date as Date1


@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            showDatePicker(this)
//            dayCardScroller()

            Navigation()
//            newScreen()
//            resourcesScreen(arrayList)
         

//            loginScreen()
            //          featuresScreen()
//            resourceFrom()
//            accountForm()
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun newScreen(){



    Column( modifier = Modifier.fillMaxSize()
//        .verticalScroll(rememberScrollState())
       ) {

        activityCircleScroller()
        Spacer(modifier = Modifier.padding(20.dp))
        contributorScroller()
        Spacer(modifier = Modifier.padding(20.dp))
        announcementScroller()
        Spacer(modifier = Modifier.padding(20.dp))
        statisticsChips()

    }





}

@Composable
fun contributor( modifier: Modifier = Modifier, name:String){
    Card(
        modifier = Modifier
            .padding(8.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color(0xFFF3F3F1)

    ) {
        Row(Modifier.padding(10.dp)) {

            Icon(Icons.Filled.Person,"",tint = Color(0xFF4552B8),
                modifier = Modifier
                    .fillMaxWidth()
                    .size(100.dp)
            )
            Text(
                buildAnnotatedString {

                    withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold, color = Color.Gray, fontSize = 15.sp)
                    ) {
                        append(name)
                    }
                }
            )


        }


    }

}


@Composable
fun announcement( modifier: Modifier = Modifier, name:String){


    Card(
        modifier = Modifier
            .padding(4.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(100.dp),
        backgroundColor = Color(0xFF4552B8)

    ) {
        Row(Modifier.padding(8.dp)) {

            Icon(Icons.Filled.Notifications,"",tint = Color(0xFFFFFFFF),
                modifier = Modifier
                    .size(30.dp)
                    .padding(5.dp)
            )
//            Spacer(modifier = Modifier.padding(9.dp))
//            Text("HI")
//            Text(text = "KJCKAHDJ")
            Text(
                buildAnnotatedString {

                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.White, fontSize = 14.sp)
                    ) {
                        append(name)
                    }
                },
                modifier = Modifier.padding(top=5.dp, bottom = 5.dp,end = 100.dp)
            )


        }


    }

}

@Composable
fun announcementScroller() {
    val items = listOf("changing plan","repairing CNC","session decisi","calculate fund",
        "changing plan","repairing CNC","session decisi","calculate fund",
        "changing plan","repairing CNC","session decisi","calculate fund",
        "changing plan","repairing CNC","session decisi","calculate fund",)

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(

            buildAnnotatedString {
//                    append("welcome to ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Normal, color = Color(
                    0xFF4552B8
                ), fontSize = 25.sp)
                ) {
                    append("Announcements")
                }
            },
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(4.dp))


        Icon(Icons.Filled.Add,"",tint = Color(0xFF4552B8),
            modifier = Modifier.size(40.dp)

        )

    }
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 100.dp),

        ) {
        itemsIndexed(items) { index, item ->
            Row( modifier=Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                announcement( modifier= Modifier,item)
                Icon(Icons.Filled.Star,"",tint = Color(0xFFC8C8CA),
                    modifier = Modifier.size(30.dp)

                )
            }


        }

    }
}



@Composable
fun contributorScroller() {
    val items = listOf("Mina","Erfan","Kiana")
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(

            buildAnnotatedString {
//                    append("welcome to ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Normal, color = Color(
                    0xFF4552B8
                ), fontSize = 25.sp)
                ) {
                    append("Contributors")
                }
            },
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(4.dp))


        Icon(Icons.Filled.Edit,"",tint = Color(0xFF4552B8),
            modifier = Modifier.size(40.dp)

        )

    }


    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp),

        ) {
        itemsIndexed(items) { index, item ->
            contributor( modifier= Modifier,item)
        }

    }
}
@Composable
fun activityCircleScroller() {
    val items = listOf("Tasks","Resources","Contributors","Announcements")

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp),

        ) {
        itemsIndexed(items) { index, item ->
            activityCircle( modifier= Modifier,"10",item)
        }

    }
}


@Composable
fun activityCircle(modifier:Modifier,content:String,caption: String){
   Column(modifier = Modifier.padding(5.dp)) {
       Box(
           contentAlignment = Alignment.Center,
           modifier = Modifier
               .size(100.dp)
               .clip(CircleShape)
               .background(Color(0xFFF3F3F3))
               .shadow(elevation = 80.dp),



       ){

           Text(

               buildAnnotatedString {
//                    append("welcome to ")
                   withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, color = Color(
                       0xFF4552B8
                   ), fontSize = 25.sp)
                   ) {
                       append(content)
                   }
               },
               textAlign = TextAlign.Center,
               modifier = Modifier.padding(4.dp),)
       }
       Text(
           buildAnnotatedString {
//                    append("welcome to ")
               withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold, color = Color(0xFF4552B8), fontSize = 15.sp)
               ) {
                   append(caption)
               }
           },
           modifier = Modifier.padding(5.dp),
           textAlign = TextAlign.Center,


       )
   }

}
@ExperimentalMaterialApi
@Composable
fun statisticCard(modifier: Modifier = Modifier,name: String) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { },
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color(0xFFF0F0ED)

    ) {
        Column(Modifier.padding(10.dp)) {
            Text(
                buildAnnotatedString {

                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Gray,
                            fontSize = 15.sp
                        )
                    ) {
                        append(name)
                    }
                }
            )

        }


    }
}
@ExperimentalMaterialApi
@Composable
fun statisticsChips() {
    val items = listOf("Running", "Upcomming", "finished")
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(

            buildAnnotatedString {
//                    append("welcome to ")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Normal, color = Color(
                            0xFF4552B8
                        ), fontSize = 25.sp
                    )
                ) {
                    append("Statistics")
                }
            },
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(4.dp)
        )


        Icon(
            Icons.Filled.Info, "", tint = Color(0xFF4552B8),
            modifier = Modifier.size(40.dp)

        )

    }


    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 100.dp, bottom = 10.dp),

        ) {
        itemsIndexed(items) { index, item ->

            statisticCard(modifier = Modifier,name = item)
        }

    }
}





@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    newScreen()
}


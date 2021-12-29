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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
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
import androidx.navigation.compose.rememberNavController


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
//            registerScreen(rememberNavController())
//            newScreen()

//            resourcesScreen(arrayList)


//            loginScreen()
            //          featuresScreen()
//            resourceFrom()
//            accountForm()
        }
    }
}

//@ExperimentalMaterialApi
//@Composable
//fun newScreen(){
//
//
//
//    Column( modifier = Modifier.fillMaxSize()
////        .verticalScroll(rememberScrollState())
//       ) {
//
//        activityCircleScroller()
//        Spacer(modifier = Modifier.padding(20.dp))
//        contributorScroller()
//        Spacer(modifier = Modifier.padding(20.dp))
//        announcementScroller()
//        Spacer(modifier = Modifier.padding(20.dp))
//        statisticsChips()
//
//    }
//
//
//
//
//
//}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
//    newScreen()
//    registerScreen(rememberNavController())
//    RadioButton()
//    loginScreen(navController = rememberNavController())
}



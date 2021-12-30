package com.example.atry

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.material.icons.outlined.CheckCircle

import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AddCircle

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.atry.data.remote.dto.*
import com.example.atry.data.remote.dto.Task
import com.example.atry.viewModels.*
import com.plcoding.ktorclientandroid.data.remote.PostsService
import com.plcoding.ktorclientandroid.data.remote.dto.PostResponse
import java.util.*


var selectedDay = mutableStateOf(1)
var userID = mutableStateOf(-1)
@Composable
public fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}
@OptIn(ExperimentalAnimationApi::class)
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun Navigation(){
    val notBar = arrayOf("login_screen","register_screen")
    val navController = rememberNavController()

    Scaffold(
        topBar ={if (currentRoute(navController = navController) !in notBar )  TopBar() },
        bottomBar = { if (currentRoute(navController = navController) !in notBar ) BottomNavigationBar(navController) }

    ) {
        NavHost(navController = navController, startDestination = Screen.loginScreen.route) {
            composable(route = Screen.loginScreen.route) {
                loginScreen(navController = navController)
            }
            composable(route = Screen.registerScreen.route) {
                registerScreen(navController = navController)
            }
            composable(
                route = Screen.featuresScreen.route + "/{name}",
                arguments = listOf(
                    navArgument("name") {
                        type = NavType.StringType
                        defaultValue = "Guest"
                        nullable = true

                    }
                )
            ) { entry ->
                featuresScreen(
                    name = entry.arguments?.getString("name"),
                    navController = navController
                )

            }
            composable(
                route = Screen.resourcesScreen.route + "/{title}",
                arguments = listOf(
                    navArgument("title") {
                        type = NavType.StringType
                        defaultValue = " "
                        nullable = true

                    }
                )
            ) { entry ->
                resourcesScreen(
                    navController = navController,
                    featureChoice = entry.arguments?.getString("title")
                )

            }

            composable(
                route = Screen.accountFormScreen.route + "/{title}",
                arguments = listOf(
                    navArgument("title") {
                        type = NavType.StringType
                        defaultValue = " "
                        nullable = true

                    }
                )
            ) { entry ->
                accountForm(featureChoice = entry.arguments?.getString("title"))

            }
            composable(
                route = Screen.resourceFormScreen.route

            ) {
                resourceFrom(navController = navController)

            }
            composable(
                route = Screen.seasonsScreen.route + "/{resourceID}",

                ) { entry ->
                seasonsScreen(
                    navController = navController,
                    id = entry.arguments?.getString("resourceID")
                )

            }
            composable(
                route = Screen.tasksScreen.route + "/{resourceID}/{month}",
                arguments = listOf(
                    navArgument("month") {
                        type = NavType.StringType
                        defaultValue = " "
                        nullable = true

                    }
                )
            ) { entry ->
                tasksScreen(
                    navController = navController,
                    id = entry.arguments?.getString("resourceID"),
                    month = entry.arguments?.getString("month")
                )

            }
            composable(
                route = Screen.taskFormScreen.route + "/{resourceID}",

                ) { entry ->
                taskForm(
                    navController = navController,
                    resourceID = entry.arguments?.getString("resourceID")
                )

            }
            composable(route = Screen.contributorScreen.route) {
                contributorForm(navController = navController)
            }
            composable(route = Screen.FAQScreen.route) {
                FAQScreen(navController = navController)
            }
            composable(route = Screen.announcementScreen.route) {
                announcementScreen(navController = navController)
            }

        }
    }
}

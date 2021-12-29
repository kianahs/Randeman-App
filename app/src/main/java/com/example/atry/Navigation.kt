package com.example.atry

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.atry.data.remote.dto.Task
import com.plcoding.ktorclientandroid.data.remote.PostsService
import com.plcoding.ktorclientandroid.data.remote.dto.PostResponse
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList


var selectedDay = mutableStateOf(1)
@Composable
public fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}
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

        }
    }
}



@Composable
fun showDatePicker(context: Context,deadLineState:String,onDateChanged: (String) -> Unit){
    val year: Int
    val month: Int
    val day: Int

    val calender = Calendar.getInstance()
    year = calender.get(Calendar.YEAR)
    month = calender.get(Calendar.MONTH)
    day = calender.get(Calendar.DAY_OF_MONTH)
    calender.time = Date()

    val date = remember {mutableStateOf("")}
    var pickedDate by remember { mutableStateOf("")}
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year:Int, month:Int, dayOfMonth: Int ->
            date.value = "$dayOfMonth/$month/$year"
        }, year, month, day
    )

    Row(
        modifier = Modifier.padding(start = 50.dp, end = 50.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
    ){
//        Text(text = "Selected Date: ${date.value}")

        pickedDate = date.value
        OutlinedTextField(
            modifier = Modifier.width(240.dp),
            value = pickedDate,
            onValueChange = onDateChanged,
            label = { Text("Deadline") },
            enabled = false,
            singleLine = true
        )
        Spacer(modifier = Modifier.padding(5.dp))
        Icon(Icons.Filled.DateRange,"",tint = Color(0xFF4552B8),modifier = Modifier
            .size(30.dp)
            .clickable { datePickerDialog.show() }
            .padding(top = 8.dp))


    }

}

@Composable
fun taskForm(navController : NavController,resourceID:String?){
    var resourceIDState by rememberSaveable { mutableStateOf(resourceID)}
    var taskNameState by rememberSaveable{mutableStateOf("")}
    var durationState by rememberSaveable{mutableStateOf("")}
    var deadlineState by rememberSaveable {mutableStateOf("")}
    var priorityState by rememberSaveable{mutableStateOf("0")}
    val addTaskViewModel:AddTaskViewModel= hiltViewModel()
    deadlineState = "3434"
    val context = LocalContext.current
    val shape = RoundedCornerShape(topStart = 80.dp)
    Column(modifier = Modifier.background(Color(0xFF4552B8))) {

        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.1f)
            .background(Color(0xFF4552B8))
        ){
            Row(modifier= Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Icon(Icons.Filled.AccountCircle,"",tint = Color.White,
                    modifier = Modifier
                        .size(50.dp)
                        .clickable { navController.navigate(Screen.accountFormScreen.route) }
                )
//

            }
        }

        Box(modifier = Modifier
            .clip(shape)
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(){
                    Text(
                        buildAnnotatedString {
//                    append("welcome to ")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, color = Color(0xFF4552B8), fontSize = 40.sp)
                            ) {
                                append("Add task")
                            }
                        }
                    )
                    Icon(Icons.Filled.CheckCircle,"",tint = Color(0xFF4552B8),
                        modifier = Modifier.size(50.dp)

                    )


                }
                Spacer(modifier = Modifier.padding(15.dp))
                OutlinedTextField(
                    value = taskNameState,
                    onValueChange = {
                        taskNameState = it
                    },
                    label = { Text("Task name") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.padding(15.dp))
                OutlinedTextField(
                    value = durationState,
                    onValueChange = {
                        durationState = it
                    },
                    label = { Text("Duration") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    singleLine = true
                )
                Spacer(modifier = Modifier.padding(15.dp))
                val year: Int
                val month: Int
                val day: Int

                val calender = Calendar.getInstance()
                year = calender.get(Calendar.YEAR)
                month = calender.get(Calendar.MONTH)
                day = calender.get(Calendar.DAY_OF_MONTH)
                calender.time = Date()

                val date = remember {mutableStateOf("")}
                val datePickerDialog = DatePickerDialog(
                    context,
                    { _: DatePicker, year:Int, month:Int, dayOfMonth: Int ->
                        date.value = "$dayOfMonth/$month/$year"
                    }, year, month, day
                )

                Row(
                    modifier = Modifier.padding(start = 50.dp, end = 50.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
                ){
//        Text(text = "Selected Date: ${date.value}")

                    deadlineState = date.value
                    OutlinedTextField(
                        modifier = Modifier.width(240.dp),
                        value = deadlineState,
                        onValueChange = {deadlineState=it},
                        label = { Text("Deadline") },
                        enabled = false,
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    Icon(Icons.Filled.DateRange,"",tint = Color(0xFF4552B8),modifier = Modifier
                        .size(30.dp)
                        .clickable { datePickerDialog.show() }
                        .padding(top = 8.dp))


                }

                Spacer(modifier = Modifier.padding(15.dp))
                OutlinedTextField(
                    value = priorityState,
                    onValueChange = {
                        priorityState = if(it === "") {
                            "0"
                        } else{
                            it.toInt().toString()
                        }
                    },
                    label = { Text("priority") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    singleLine = true
                )
                Spacer(modifier = Modifier.padding(15.dp))
                if (resourceID != null) {
                    OutlinedTextField(
                        value = resourceIDState.toString(),
                        onValueChange = {
                            resourceIDState = it
                        },
                        label = { Text("Resource ID") },
                        enabled = false,
                        singleLine = true
                    )
                }
                Spacer(modifier = Modifier.padding(15.dp))
                Icon(Icons.Filled.AddCircle,"",tint = Color(0xFF4552B8),modifier = Modifier
                    .size(130.dp)
                    .padding(bottom = 50.dp)
                    .clickable {
                        val task: com.example.atry.data.remote.dto.Task =
                            com.example.atry.data.remote.dto.Task(
                                " ",
                                durationState.toInt(),
                                taskNameState,
                                priorityState.toInt(),
                                deadlineState,
                                -1,
                                " "
                            )
                        if (resourceID != null) {
                            addTaskViewModel.addResource(resourceID.toInt(), task = task)
                        }
                        navController.navigate(Screen.tasksScreen.withArgs(resourceID))
                    }) //bayad eslah she be resource id
//                Spacer(modifier = Modifier.padding(15.dp))

            }

        }



    }



}


@ExperimentalMaterialApi
@Composable
fun tasksScreen(navController: NavController,id:String?, month:String?){
    val getTaskViewModel:GetTaskViewModel = hiltViewModel()
    if (id != null && !getTaskViewModel.dataLoaded.value) {
        getTaskViewModel.getTask(id.toInt())
    }


    var count by remember { mutableStateOf(0) }
    val context = LocalContext.current

    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxWidth()) {


        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween){
            Column() {
                Text(
                    buildAnnotatedString {
//                    append("welcome to ")

                        withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, color = Color(0xFF4552B8), fontSize = 40.sp)
                        ) {
                            append("Today")
                        }

                    },
                    modifier = Modifier.padding(10.dp)
                )
                Text(
                    buildAnnotatedString {
//                    append("welcome to ")

                        withStyle(style = SpanStyle(fontWeight = FontWeight.Normal, color = Color(
                            0xFFB0B0B3
                        ), fontSize = 10.sp)
                        ) {
                            append("resource_id :$id ")
                        }

                    },
                    modifier = Modifier.padding(start=10.dp,bottom = 5.dp, top=5.dp)
                )
            }

            Text(
                buildAnnotatedString {
//                    append("welcome to ")

                    withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold, color = Color(0xFF4552B8), fontSize = 20.sp)
                    ) {
                        append("$month")
                    }

                },
                modifier = Modifier.padding(10.dp)
            )
        }




        dayCardScroller(viewmodel = getTaskViewModel)

    }
    CircularProgressBar(isDisplayed = getTaskViewModel.state.value.isLoading)


    LazyColumn(modifier = Modifier.padding(top=200.dp, start = 50.dp,end=10.dp, bottom = 50.dp)){

        itemsIndexed(
            getTaskViewModel.state.value.tasks
        ){index, item ->
            Box(){
                Row() {
                    Icon(Icons.Filled.Info,"",tint = Color(0xFF4552B8),
                        modifier = Modifier
                            .size(40.dp)
                            .padding(top = 20.dp)
                    )

                    taskCard(modifier = Modifier,task = item)

                }

            }

        }
    }

    Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.End , modifier = Modifier.fillMaxSize()){
        Icon(Icons.Filled.AddCircle,"",tint = Color(0xFF4552B8),
            modifier = Modifier
                .size(80.dp)

                .clickable { navController.navigate(Screen.taskFormScreen.withArgs(id.toString())) })
    }


}

@ExperimentalMaterialApi
@Composable
fun taskCard(  modifier: Modifier = Modifier, task: Task){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clickable { },
        elevation = 10.dp,
        shape = RoundedCornerShape(20.dp),
        backgroundColor = Color(0xFF673AB7)

    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(Modifier.padding(top=20.dp)) {
                Text(
                    buildAnnotatedString {
//                    append("welcome to ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, color = Color.White, fontSize = 20.sp)
                        ) {
                            append(task.name)
                        }
                    }
                )

                Text(buildAnnotatedString {
//                    append("welcome to ")

                    withStyle(style = SpanStyle(color = Color.White, fontSize = 10.sp),

                        ) {
                        append("Resource:"+"\n")
                        append("Priority:"+task.priority.toString()+"\n")
                        append("Duration:"+task.duration.toString()+"\n")
                    }
                })
            }
            Column() {
                Image(painter = painterResource(id = R.drawable.task), contentDescription = null ,Modifier.size(80.dp))
            }
        }

    }


}

@ExperimentalMaterialApi
@Composable
fun dayCard(  modifier: Modifier = Modifier, date: Date, viewmodel: GetTaskViewModel){

    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { viewmodel.deleteTasks() },
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color(0xFFF3F3F1)

    ) {
        Column(Modifier.padding(10.dp)) {
            Text(
                buildAnnotatedString {

                    withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold, color = Color.Gray, fontSize = 15.sp)
                    ) {
                        append(date.getDayName())
                    }
                }
            )

            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold,color = Color.Black, fontSize = 15.sp),


                        ) {
                        append(date.getDayNumber())
                    }
                },
                modifier = Modifier.padding(start= 8.dp, end= 8.dp))
        }



    }


}
@ExperimentalMaterialApi
@Composable
fun dayCardScroller(viewmodel: GetTaskViewModel) {
    val items = listOf<Date>(Date("Sat", "1"),Date("Sun", "2"),
        Date("Mon", "3"),
        Date("Tue", "4"),
        Date("Wed", "5"),
        Date("Thu", "6"),
        Date("Fri", "7"))

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 100.dp, bottom = 10.dp),

        ) {
        itemsIndexed(items) { index, item ->
            dayCard( modifier= Modifier,item, viewmodel)
        }

    }
}



@Composable
fun resourceFrom(navController: NavController) {
    val addViewModel: AddResourcesViewModel = hiltViewModel()
    var resourceNameState  by rememberSaveable { mutableStateOf("") }
    var resourceDescriptionState by rememberSaveable { mutableStateOf("") }
    val shape = RoundedCornerShape(topStart = 80.dp)

    Column(modifier = Modifier.background(Color(0xFF4552B8))) {

        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.35f)
            .background(Color(0xFF4552B8))
        )


        Box(modifier = Modifier
            .clip(shape)
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    buildAnnotatedString {
//                    append("welcome to ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, color = Color(0xFF4552B8), fontSize = 40.sp)
                        ) {
                            append("Add resource!")
                        }
                    }
                )
                Spacer(modifier = Modifier.padding(15.dp))
                OutlinedTextField(
                    value = resourceNameState,
                    onValueChange = { resourceNameState = it },
                    label = { Text("Enter Resource name") }
                )
                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(
                    value = resourceDescriptionState,
                    onValueChange = { resourceDescriptionState = it },
                    label = { Text("Enter Description") },
                )
                Spacer(modifier = Modifier.padding(15.dp))
                Icon(Icons.Filled.AddCircle,"",tint = Color(0xFF4552B8),
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {

                            val resource: com.example.atry.data.remote.dto.Resource =
                                com.example.atry.data.remote.dto.Resource(
                                    -1,
                                    "null",
                                    resourceDescriptionState,
                                    resourceNameState,
                                    -1
                                )
                            addViewModel.addResource(resource)


                            navController.navigate(Screen.resourcesScreen.withArgs("Resources"))
                        })



            }

        }



    }

}

@Composable
fun accountForm(featureChoice: String?) {
    val resourceNameState = remember { mutableStateOf(TextFieldValue()) }
    val resourceDescriptionState = remember { mutableStateOf(TextFieldValue()) }
    val shape = RoundedCornerShape(topStart = 80.dp)
    val shape2 = CircleShape
    Column(modifier = Modifier.background(Color(0xFF4552B8)),horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.10f)
            .background(Color(0xFF4552B8))
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.ExtraBold, color = Color(0xFFABA0E7), fontSize = 40.sp
                            )
                        ) {
                            append("Edit Profile")
                        }
                    }
                )
            }
        }
        Box(modifier = Modifier
            .clip(shape)
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier
                    .size(120.dp)

                    .clip(shape2)
                    .background(Color.Gray)
                ){

                    Spacer(modifier = Modifier.padding(5.dp))
                    Icon(Icons.Filled.Person,"",tint = Color(0xFFA3ACEE),modifier = Modifier.size(120.dp))

                }

                Box(modifier = Modifier
                    .fillMaxWidth()

                    .background(Color.White)

                ){
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.padding(5.dp))
                        Icon(Icons.Filled.AddCircle,"photo",tint = Color(0xFFB9BDDA),modifier = Modifier.size(40.dp))


                    }
                }
                textInput(textFieldName = "Your name", true)
                Spacer(modifier = Modifier.padding(5.dp))
                textInput(textFieldName = "Username",true)
                Spacer(modifier = Modifier.padding(5.dp))
                textInput(textFieldName = "Password", false)
                Spacer(modifier = Modifier.padding(5.dp))
                textInput(textFieldName = "Company name", true)
                Spacer(modifier = Modifier.padding(0.5.dp))
                Icon(Icons.Outlined.CheckCircle,"",tint = Color(0xFF626CC2),modifier = Modifier.size(40.dp))


            }

        }



    }

}



@ExperimentalMaterialApi
@Composable
fun  resourcesScreen( navController: NavController,featureChoice:String?){
    val viewModel:ResourcesViewModel = hiltViewModel()

    val service = PostsService.create()
    val posts = produceState<List<PostResponse>>(
        initialValue = emptyList(),
        producer = {
            value = service.getPosts()
        }
    )
    CircularProgressBar(isDisplayed = viewModel.state.value.isLoading)
    LazyColumn(modifier = Modifier.padding(bottom = 50.dp)){

        itemsIndexed(viewModel.state.value.resources){index, item ->
            resourceCard(navController = navController,item.unique_id,item.name,item.description, Modifier.fillMaxSize(),{Icon(Icons.Filled.Settings,"",tint = Color(0xFF4552B8),modifier = Modifier.size(40.dp))})
        }
    }
    Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.End , modifier = Modifier.fillMaxSize()){
        Icon(Icons.Filled.AddCircle,"",tint = Color(0xFF4552B8),
            modifier = Modifier
                .size(130.dp)
                .padding(bottom = 50.dp)
                .clickable { navController.navigate(Screen.resourceFormScreen.route) })
    }


}


@ExperimentalMaterialApi
@Composable
fun resourceCard( navController: NavController,id:Int,resourceName:String,description:String, modifier: Modifier = Modifier, icon_shape: @Composable() () -> Unit){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { navController.navigate(Screen.seasonsScreen.withArgs(id.toString())) },
        elevation = 10.dp,
        shape = RoundedCornerShape(15.dp),
        backgroundColor = Color(0xFFE3DFDC)

    ) {
        Image(painter = painterResource(id = R.drawable.resource), contentDescription = null)
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
//                    append("welcome to ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, color = Color(0xFF4552B8), fontSize = 25.sp)
                        ) {
                            append(resourceName)
                        }
                    }
                )




            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(buildAnnotatedString {
//                    append("welcome to ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, color = Color(0xFF4552B8))
                ) {
                    append("Discription:\n")
                }
                withStyle(style = SpanStyle(color = Color.Gray)
                ) {
                    append(description)
                }
            })
        }


    }


}


@Composable
fun loginScreen(navController: NavController){

    var usernameState by rememberSaveable { mutableStateOf("") }
    var passwordState by rememberSaveable { mutableStateOf("") }
    Box(modifier = Modifier
        .background(Color(0xFF6C5DBD))
        .fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

//        Text(
//            buildAnnotatedString {
////                    append("welcome to ")
//                withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, color = Color(0xFF4552B8), fontSize = 50.sp)
//                ) {
//                    append("Welcome !")
//                }
//            }
//        )
//        Spacer(modifier = Modifier.padding(15.dp))
            Box(modifier = Modifier
                .background(Color.White, RoundedCornerShape(40.dp))
                .fillMaxHeight(0.65f)
                .fillMaxWidth(0.8f)
                .clip(
                    RoundedCornerShape(40.dp)
                )) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painterResource(R.drawable.logop75),"logo")
                    TextField(
                        value = usernameState,
                        onValueChange = { usernameState = it },
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                        label = { Text("Username")
                        }
                    )
//        textInput(textFieldName = "Username",true)
                    Spacer(modifier = Modifier.padding(5.dp))
                    TextField(
                        value = passwordState,
                        onValueChange = { passwordState = it },
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                        label = { Text("password") },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )
                    Spacer(modifier = Modifier.padding(15.dp))
                    Button(modifier = Modifier.size(250.dp,50.dp),shape = RoundedCornerShape(50),colors = ButtonDefaults.buttonColors(backgroundColor = Color(
                        0xFF6C5DBD
                    )
                    ),onClick = { navController.navigate(Screen.featuresScreen.withArgs(usernameState))}) {
                        Text(fontWeight = FontWeight.Bold,color = Color.White,text = "Login")

                    }

                }


            }

            Spacer(modifier = Modifier.padding(10.dp))
            Text(
                buildAnnotatedString {
//                    append("welcome to ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, color = Color(
                        0xFFF8F7F7
                    ), fontSize = 12.sp)
                    ) {
                        append("Register")
                    }
                },
                modifier = Modifier.clickable { navController.navigate(Screen.registerScreen.route) }
            )

        }



    }


}
@Composable
fun textInput(textFieldName: String, show: Boolean ) {

    var textState by rememberSaveable { mutableStateOf("") }
    if (show) {
        OutlinedTextField(
            value = textState,
            onValueChange = { textState = it },
            label = { Text("Enter $textFieldName") }
        )
    }else{
        OutlinedTextField(
            value = textState,
            onValueChange = { textState = it },
            label = { Text("Enter $textFieldName") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
    }
}

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
            .padding(top = 10.dp, bottom = 60.dp),

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

@ExperimentalMaterialApi
@Composable
fun cardElement( title:String, modifier: Modifier = Modifier, icon_shape: @Composable() () -> Unit, navController: NavController){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { },
        elevation = 10.dp,
        shape = RoundedCornerShape(15.dp),

        onClick = {
            when(title){

                "Account" -> navController.navigate(Screen.accountFormScreen.withArgs(title))
                "Resources" -> navController.navigate(Screen.resourcesScreen.withArgs(title))


            }

        }

    ) {
        Row(
            modifier = Modifier.padding(50.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                buildAnnotatedString {
//                    append("welcome to ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, color = Color(0xFF4552B8), fontSize = 25.sp)
                    ) {
                        append(title)
                    }
                }
            )
            icon_shape()


        }
    }


}
@ExperimentalMaterialApi
@Composable
fun seasonsScreen(navController: NavController,id:String?) {
    val service = PostsService.create()
    val items = listOf("Winter","Spring","Summer","Autumn").map { " $it" }
    val month1= listOf("January","February","March")
    val month2= listOf("  April","    May","   June")
    val month3= listOf("    July","  August","September")
    val month4= listOf("October","November","December")
    val col = listOf<Long>(0xFF5DA0D1,0xFF669E68,0xFFE0D29D,0xFFCC845E)
    LazyColumn(modifier = Modifier.padding(bottom = 50.dp)) {
        itemsIndexed(items) { index, item ->

            if(index==0)
                seasons(navController,item,month1,col[index],id)
            if(index==1)
                seasons(navController,item,month2,col[index],id)
            if(index==2)
                seasons(navController,item,month3,col[index],id)
            if(index==3)
                seasons(navController,item,month4,col[index],id)
        }

    }
}
@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = "Resource Manager", fontSize = 18.sp) },
        backgroundColor = colorResource(id = R.color.purple_500),
        contentColor = Color.White
    )
}


@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Resources,
        NavigationItem.Setting,
        NavigationItem.Account,
        NavigationItem.FAQ
    )
    BottomNavigation(

        backgroundColor = colorResource(id = R.color.purple_500),
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title,modifier = Modifier.size(21.dp)) },
                label = { Text(text = item.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    if(item.route ==NavigationItem.Home.route ){
                        navController.navigate(Screen.featuresScreen.withArgs("ss"))
                    }
                    if(item.route ==NavigationItem.Resources.route ){
                        navController.navigate(Screen.resourcesScreen.withArgs("Resources"))
                    }
                    if(item.route ==NavigationItem.Account.route ){
                        navController.navigate(Screen.accountFormScreen.withArgs("mm"))
                    }
//                    navController.navigate(item.route) {
//
//                        // Pop up to the start destination of the graph to
//                        // avoid building up a large stack of destinations
//                        // on the back stack as users select items
//                        navController.graph.startDestinationRoute?.let { route ->
//                            popUpTo(route) {
//                                saveState = true
//                            }
//                        }
//                        // Avoid multiple copies of the same destination when
//                        // reselecting the same item
//                        launchSingleTop = true
//                        // Restore state when reselecting a previously selected item
//                        restoreState = true
//                    }
                }
            )
        }
    }
}
@ExperimentalMaterialApi
@Composable
fun seasons(navController: NavController,name :String, d : List<String>, col : Long,id:String?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
        ,
        elevation = 10.dp,
        shape = RoundedCornerShape(15.dp),
        backgroundColor = Color(col)


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
                                Text("  "+d.get(index),color = Color(col),fontFamily = FontFamily.Serif,fontSize = 18.sp,fontWeight = FontWeight.ExtraBold) // card's content


                            }
                        }

                    }
                }
            }
        }


    }
}


@Composable
fun registerScreen(navController: NavController){

    var firstnameState by rememberSaveable { mutableStateOf("") }
    var lastnameState by rememberSaveable { mutableStateOf("") }
    var EmailState by rememberSaveable { mutableStateOf("") }
    var passwordState by rememberSaveable { mutableStateOf("") }

    Box(modifier = Modifier
        .background(Color(0xFF6C5DBD))
        .fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Box(modifier = Modifier
                .background(Color.White, RoundedCornerShape(40.dp))
                .fillMaxHeight(0.8f)
                .fillMaxWidth(0.8f)
                .clip(
                    RoundedCornerShape(40.dp)
                )) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painterResource(R.drawable.logop75),"logo")
                    TextField(
                        value = firstnameState,
                        onValueChange = { firstnameState = it },
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                        label = { Text("Name")
                        }
                    )
//        textInput(textFieldName = "Username",true)
                    Spacer(modifier = Modifier.padding(5.dp))
                    TextField(
                        value = lastnameState,
                        onValueChange = { lastnameState = it },
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                        label = { Text("Lastname")
                        }
                    )
//        textInput(textFieldName = "Username",true)
                    Spacer(modifier = Modifier.padding(5.dp))
                    TextField(
                        value = EmailState,
                        onValueChange = { EmailState = it },
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                        label = { Text("Email")
                        }
                    )
//        textInput(textFieldName = "Username",true)
                    Spacer(modifier = Modifier.padding(5.dp))
                    TextField(
                        value = passwordState,
                        onValueChange = { passwordState = it },
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                        label = { Text("Password") },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    RadioButton()
                    Spacer(modifier = Modifier.padding(10.dp))
                    Button(modifier = Modifier.size(250.dp,50.dp),shape = RoundedCornerShape(50),colors = ButtonDefaults.buttonColors(backgroundColor = Color(
                        0xFF6C5DBD
                    )
                    ),onClick = { navController.navigate(Screen.featuresScreen.withArgs(firstnameState))}) {
                        Text(fontWeight = FontWeight.Bold,color = Color.White,text = "Register")

                    }
//                    Spacer(modifier = Modifier.padding(1.dp))

                }

            }
            Spacer(modifier = Modifier.padding(10.dp))
            Text(
                buildAnnotatedString {
//                    append("welcome to ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, color = Color(
                        0xFFF8F7F7
                    ), fontSize = 12.sp)
                    ) {
                        append("Login")
                    }
                },
                modifier = Modifier.clickable { navController.navigate(Screen.loginScreen.route) }
            )
        }





    }


}


@Composable
fun RadioButton() {
    Column(

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val selectedType = remember { mutableStateOf("") }
        Spacer(modifier = Modifier.size(5.dp))
        Row {
            RadioButton(selected = selectedType.value == "Company", onClick = {
                selectedType.value = "Company"
            })
            Spacer(modifier = Modifier.size(5.dp))
            Text("Company")
            Spacer(modifier = Modifier.size(16.dp))
            RadioButton(selected = selectedType.value == "Contributor", onClick = {
                selectedType.value = "Contributor"
            })
            Spacer(modifier = Modifier.size(5.dp))
            Text("Contributor")
        }
    }
}




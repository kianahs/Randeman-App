package com.example.atry

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.atry.Date
import com.example.atry.data.remote.dto.Task
import com.example.atry.viewModels.AddTaskViewModel
import java.util.*

@Composable
fun taskForm(navController : NavController, resourceID:String?){
    var resourceIDState by rememberSaveable { mutableStateOf(resourceID) }
    var taskNameState by rememberSaveable{ mutableStateOf("") }
    var durationState by rememberSaveable{ mutableStateOf("") }
    var deadlineState by rememberSaveable { mutableStateOf("") }
    var priorityState by rememberSaveable{ mutableStateOf("0") }
    val addTaskViewModel: AddTaskViewModel = hiltViewModel()
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
                Icon(
                    Icons.Filled.AccountCircle,"",tint = Color.White,
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
                    Icon(
                        Icons.Filled.CheckCircle,"",tint = Color(0xFF4552B8),
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

                val date = remember { mutableStateOf("") }
                val datePickerDialog = DatePickerDialog(
                    context,
                    { _: DatePicker, year:Int, month:Int, dayOfMonth: Int ->
                        date.value = "$dayOfMonth/${month+1}/$year"
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
                    Icon(
                        Icons.Filled.DateRange,"",tint = Color(0xFF4552B8),modifier = Modifier
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
                Icon(
                    Icons.Filled.AddCircle,"",tint = Color(0xFF4552B8),modifier = Modifier
                    .size(130.dp)
                    .padding(bottom = 50.dp)
                    .clickable {
                        val task: Task =
                            Task(
                                " ",
                                durationState.toInt(),
                                taskNameState,
                                priorityState.toInt(),
                                deadlineState,
                                -1,
                                " ",
                                " ",
                                " "
                            )
                        if (resourceID != null) {
                            addTaskViewModel.addResource(resourceID.toInt(), task = task)
                        }
                        navController.navigate(Screen.seasonsScreen.withArgs(resourceID))
                    }) //bayad eslah she be resource id
//                Spacer(modifier = Modifier.padding(15.dp))

            }

        }



    }



}
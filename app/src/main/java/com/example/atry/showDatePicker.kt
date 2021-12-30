package com.example.atry

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.atry.Date
import java.util.*


@Composable
fun showDatePicker(context: Context, deadLineState:String, onDateChanged: (String) -> Unit){
    val year: Int
    val month: Int
    val day: Int

    val calender = Calendar.getInstance()
    year = calender.get(Calendar.YEAR)
    month = calender.get(Calendar.MONTH)
    day = calender.get(Calendar.DAY_OF_MONTH)
    calender.time = Date()

    val date = remember { mutableStateOf("") }
    var pickedDate by remember { mutableStateOf("") }
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
        Icon(
            Icons.Filled.DateRange,"",tint = Color(0xFF4552B8),modifier = Modifier
            .size(30.dp)
            .clickable { datePickerDialog.show() }
            .padding(top = 8.dp))


    }

}
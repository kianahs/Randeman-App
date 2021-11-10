package com.example.atry

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.atry.ui.theme.TryTheme

class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

                    Column() {

                        topic_element( "Resources",  Modifier.fillMaxSize())
                        topic_element( "Tasks",  Modifier.fillMaxSize())

                    }


        }
    }
}
@ExperimentalMaterialApi
@Composable
fun topic_element( title:String, modifier: Modifier = Modifier){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { },
        elevation = 10.dp,
        shape = RoundedCornerShape(15.dp)

    ) {
        Row(
            modifier = Modifier.padding(30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                buildAnnotatedString {
//                    append("welcome to ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.W900, color = Color(0xFF4552B8))
                    ) {
                        append(title)
                    }
                }
            )

            Icon(Icons.Filled.Add,"",tint = Color(0xFF4552B8))
//
        }
    }

//    Button(
//        modifier = Modifier.background(Color.Yellow),
//        onClick = { /* ... */ },
//        // Uses ButtonDefaults.ContentPadding by default
//        contentPadding = PaddingValues(
//            start = 20.dp,
//            top = 12.dp,
//            end = 20.dp,
//            bottom = 12.dp
//        )
//    ) {
//
//        Text(name)
//    }
}
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    TryTheme {
//        Greeting("Android")
//    }
//}

class Resource (val resource_name: String, val resource_ID: Int){

    val tasks_list: MutableList<Task> = ArrayList()
    val name = resource_name
    val ID = resource_ID

    fun assign_task_to_resource(task: Task){

        tasks_list.add(task)
    }

    fun sort_tasks_by_priorities(){

        tasks_list.sortByDescending { task -> task.get_task_priority() }

        for(task in tasks_list){
            print(task)
        }


    }




}


class Task (val task_name: String, val task_ID: Int, val task_duration: Int, val task_priority: Int ){

    val name = task_name
    val ID = task_ID
    val duration = task_duration
    val priority = task_priority

    fun get_task_priority(): Int{

        return priority
    }
    fun get_task_name(): String{
        return name
    }

    override fun toString(): String {
        return "Task(task_name='$task_name')"
    }


}

fun start(){

    val a1 = Task("a1", 1, 20,100)
    val a2 = Task("a2", 2, 20,700)
    val a3 = Task("a3", 3, 20,500)

    val CNC = Resource("CNC", 1)
    CNC.assign_task_to_resource(a2)
    CNC.assign_task_to_resource(a1)
    CNC.assign_task_to_resource(a3)

    CNC.sort_tasks_by_priorities()

}
package com.example.atry

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atry.ui.theme.TryTheme

class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {


            LazyColumn() {
                itemsIndexed(
                    listOf<cardStructure>(
                        cardStructure("Recources", {Icon(Icons.Filled.Add,"",tint = Color(0xFF4552B8),modifier = Modifier.size(40.dp))}),
                        cardStructure("Tasks", {Icon(Icons.Filled.Add,"",tint = Color(0xFF4552B8),modifier = Modifier.size(40.dp))}),
                    cardStructure("Setting", {Icon(Icons.Filled.Settings,"",tint = Color(0xFF4552B8),modifier = Modifier.size(40.dp))}),
                    cardStructure("Account", {Icon(Icons.Filled.Person,"",tint = Color(0xFF4552B8),modifier = Modifier.size(40.dp))}),
                    cardStructure("FAQ", {Icon(Icons.Filled.Search,"",tint = Color(0xFF4552B8),modifier = Modifier.size(40.dp))}))

                ) { index, item ->
                    cardElement(item.get_title(), Modifier.fillMaxSize(),item.get_icon())
                }
//                itemsIndexed(
//                    Array({"hi",41},{""})
//                )


            }
        }
    }
}

class cardStructure(val title: String, icon: @Composable() () -> Unit){

     val item_title = title
     val item_icon = icon

    fun get_title(): String{

        return item_title
    }

    fun get_icon(): @Composable() () -> Unit{
        return item_icon
    }


}


@ExperimentalMaterialApi
@Composable
fun cardElement( title:String, modifier: Modifier = Modifier, icon_shape: @Composable() () -> Unit){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { },
        elevation = 10.dp,
        shape = RoundedCornerShape(15.dp)

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
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

//@ExperimentalMaterialApi
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//
//    LazyColumn() {
//        itemsIndexed(
//            listOf("Recources", "Tasks", "Setting", "Account", "FAQ")
//
//        ) { index, string ->
//            cardElement(string, Modifier.fillMaxSize())
//        }
//
//
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
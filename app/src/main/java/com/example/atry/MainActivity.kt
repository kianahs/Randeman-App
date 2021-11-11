package com.example.atry

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import java.sql.*
import java.util.*
import com.example.atry.*
import kotlin.collections.ArrayList

val arrayList = ArrayList<Resource>()

class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arrayList.add(Resource("CNC1","lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum"))
        arrayList.add(Resource("CNC2","lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum"))
        arrayList.add(Resource("CNC3","lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum"))
        arrayList.add(Resource("CNC4","lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem "))

        setContent {


//            resourcesScreen(arrayList)

//            loginScreen()
//            featuresScreen()
            resourceFrom()



        }
    }
}

@Composable
fun resourceFrom() {

    val resourceNameState = remember { mutableStateOf(TextFieldValue()) }
    val resourceDescriptionState = remember { mutableStateOf(TextFieldValue()) }
    val shape = RoundedCornerShape(topStart = 80.dp)
    Column(modifier = Modifier.background(Color(0xFF4552B8))) {

        Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.35f).background(Color(0xFF4552B8))
            )

        Box(modifier = Modifier.clip(shape).fillMaxWidth().fillMaxHeight().background(Color.White)
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
                textInput(textFieldName = "Resource name",true)
                Spacer(modifier = Modifier.padding(5.dp))
                textInput(textFieldName = "Description", false)
                Spacer(modifier = Modifier.padding(15.dp))
                Icon(Icons.Filled.AddCircle,"",tint = Color(0xFF4552B8),modifier = Modifier.size(40.dp))


            }

        }



    }

}

@Composable
fun loginScreen(){

    val usernameState = remember { mutableStateOf(TextFieldValue()) }
    val passwordState = remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            buildAnnotatedString {
//                    append("welcome to ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, color = Color(0xFF4552B8), fontSize = 50.sp)
                ) {
                    append("Welcome !")
                }
            }
        )
        Spacer(modifier = Modifier.padding(15.dp))
        textInput(textFieldName = "Username",true)
        Spacer(modifier = Modifier.padding(5.dp))
        textInput(textFieldName = "Password", false)
        Spacer(modifier = Modifier.padding(15.dp))
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Login")

        }


    }
   


}
@Composable
fun textInput(textFieldName: String, show: Boolean ) {
    var textState by rememberSaveable { mutableStateOf("") }
    if (show) {
        TextField(
            value = textState,
            onValueChange = { textState = it },
            label = { Text("Enter $textFieldName") }
        )
    }else{
        TextField(
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
fun featuresScreen (){

    LazyColumn() {
        itemsIndexed(
            listOf<cardStructure>(
                cardStructure("Resources", {Icon(Icons.Filled.Add,"",tint = Color(0xFF4552B8),modifier = Modifier.size(40.dp))}),
                cardStructure("Tasks", {Icon(Icons.Filled.Add,"",tint = Color(0xFF4552B8),modifier = Modifier.size(40.dp))}),
                cardStructure("Setting", {Icon(Icons.Filled.Settings,"",tint = Color(0xFF4552B8),modifier = Modifier.size(40.dp))}),
                cardStructure("Account", {Icon(Icons.Filled.Person,"",tint = Color(0xFF4552B8),modifier = Modifier.size(40.dp))}),
                cardStructure("FAQ", {Icon(Icons.Filled.Search,"",tint = Color(0xFF4552B8),modifier = Modifier.size(40.dp))}))

        ) { index, item ->
            cardElement(item.get_title(), Modifier.fillMaxSize(),item.get_icon())
        }

    }

}

@ExperimentalMaterialApi
@Composable
fun resourcesScreen(resources: List<Resource>){
    LazyColumn(){
        itemsIndexed(resources){index, item ->
            resourceCard(item.name,item.description, Modifier.fillMaxSize(),{Icon(Icons.Filled.Settings,"",tint = Color(0xFF4552B8),modifier = Modifier.size(40.dp))})
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
fun resourceCard( resourceName:String,description:String, modifier: Modifier = Modifier, icon_shape: @Composable() () -> Unit){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { },
        elevation = 10.dp,
        shape = RoundedCornerShape(15.dp),
        backgroundColor = Color(0xFFE3DFDC)


    ) {
        Image(painter = painterResource(id = R.drawable.resource), contentDescription = null)
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.padding(20.dp)
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

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

   resourceFrom()

}

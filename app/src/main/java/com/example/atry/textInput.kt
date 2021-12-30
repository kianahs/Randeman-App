package com.example.atry

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation



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
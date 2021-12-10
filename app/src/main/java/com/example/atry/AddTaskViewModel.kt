package com.example.atry
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atry.data.remote.RetroApi
import com.example.atry.data.remote.dto.Resource
import com.example.atry.data.remote.dto.Task
import com.plcoding.ktorclientandroid.data.remote.dto.PostResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val api:RetroApi

):ViewModel(){

    private val _state = mutableStateOf(AddResourceState())
    val state: State<AddResourceState> = _state

    fun addResource(id:Int,task:Task){
        viewModelScope.launch {
            try {
                _state.value = state.value.copy(isLoading = true)
                _state.value = state.value.copy(
                    statusCode = api.addTask(id,task),
                    isLoading = false
                )

            }
            catch (e:Exception){
                Log.e("AddResourceViewModel","addResources:",e)
                _state.value = state.value.copy(isLoading = false,error="error")

            }
        }

    }

}
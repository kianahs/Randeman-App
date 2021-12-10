package com.example.atry
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atry.data.remote.RetroApi
import com.example.atry.data.remote.dto.Resource
import com.plcoding.ktorclientandroid.data.remote.dto.PostResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetTaskViewModel @Inject constructor(
    private val api:RetroApi

):ViewModel(){

    private val _state = mutableStateOf(TaskListState())
    val state: State<TaskListState> = _state
    val loading = mutableStateOf(false)
    val dataLoaded = mutableStateOf(false)


    fun getTask(id:Int){
        viewModelScope.launch {
            loading.value = true
            try {
                _state.value = state.value.copy(isLoading = true)
                delay(2000)
                _state.value = state.value.copy(
                    tasks = api.getTask(id),
                    isLoading = false
                )
                dataLoaded.value = true

            loading.value = false
            }
            catch (e:Exception){
                Log.e("GetTaskViewModel","GetTaskViewModel:",e)
                _state.value = state.value.copy(isLoading = false,error="error")
                dataLoaded.value = false
                loading.value = true

            }
        }


    }


    fun deleteTasks(){
        viewModelScope.launch {
            _state.value = state.value.copy(tasks = listOf())
        }
    }

}
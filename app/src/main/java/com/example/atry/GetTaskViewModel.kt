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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetTaskViewModel @Inject constructor(
    private val api:RetroApi

):ViewModel(){

    private val _state = mutableStateOf(TaskListState())
    val state: State<TaskListState> = _state

    fun getTask(id:Int){
        viewModelScope.launch {
            try {
                _state.value = state.value.copy(isLoading = true)
                _state.value = state.value.copy(
                    tasks = api.getTask(id),
                    isLoading = false
                )

            }
            catch (e:Exception){
                Log.e("GetTaskViewModel","GetTaskViewModel:",e)
                _state.value = state.value.copy(isLoading = false,error="error")

            }
        }

    }

}
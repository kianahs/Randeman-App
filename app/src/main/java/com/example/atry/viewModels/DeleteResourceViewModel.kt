package com.example.atry.viewModels
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atry.viewModels.stateModels.PostResponseState
import com.example.atry.data.remote.RetroApi
import com.example.atry.data.remote.dto.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeleteResourceViewModel @Inject constructor(
    private val api:RetroApi

):ViewModel(){

    private val _state = mutableStateOf(PostResponseState())
    val state: State<PostResponseState> = _state

    fun deleteResource(id:Int){
        viewModelScope.launch {
            try {
                _state.value = state.value.copy(isLoading = true)
                _state.value = state.value.copy(
                    statusCode = api.deleteResource(id),
                    isLoading = false
                )

            }
            catch (e:Exception){
                Log.e("DeleteResourceViewModel","deleteResource:",e)
                _state.value = state.value.copy(isLoading = false,error="error")

            }
        }

    }

}
package com.example.atry.viewModels
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atry.viewModels.stateModels.PostResponseState
import com.example.atry.data.remote.RetroApi
import com.example.atry.data.remote.dto.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddResourcesViewModel @Inject constructor(
    private val api:RetroApi

):ViewModel(){

    private val _state = mutableStateOf(PostResponseState())
    val state: State<PostResponseState> = _state

    fun addResource(resource:Resource){
        viewModelScope.launch {
            try {
                _state.value = state.value.copy(isLoading = true)
                _state.value = state.value.copy(
                    statusCode = api.addResource(resource),
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
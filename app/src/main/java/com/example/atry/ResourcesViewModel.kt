package com.example.atry
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atry.data.remote.RetroApi
import com.plcoding.ktorclientandroid.data.remote.PostsService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResourcesViewModel @Inject constructor(
    private val api:RetroApi

):ViewModel(){

    private val _state = mutableStateOf(ResourceListState())
    val state: State<ResourceListState> = _state
    init {
        getResources()
    }
    fun getResources(){
        viewModelScope.launch {
            try {
                _state.value = state.value.copy(isLoading = true)
                _state.value = state.value.copy(
                    resources = api.getResources(),
                    isLoading = false
                )


            }catch (e:Exception){
                Log.e("MainViewModel","getResources:",e)
                _state.value = state.value.copy(isLoading = false,error="error")

            }
        }
    }
}
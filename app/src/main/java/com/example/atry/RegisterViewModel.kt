package com.example.atry
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atry.data.remote.RetroApi
import com.example.atry.data.remote.dto.Login
import com.example.atry.data.remote.dto.Register
import com.example.atry.data.remote.dto.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val api:RetroApi

):ViewModel(){

    private val _state = mutableStateOf(PostResponseState())
    val state: State<PostResponseState> = _state

    fun register(registerData: Register){
        viewModelScope.launch {
            try {
                _state.value = state.value.copy(isLoading = true)
                _state.value = state.value.copy(
                    statusCode = api.register(registerData),
                    isLoading = false
                )

            }
            catch (e:Exception){
                Log.e("RegisterViewModel","Register:",e)
                _state.value = state.value.copy(isLoading = false,error=e.toString())

            }
        }

    }

}
package com.example.atry.viewModels
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atry.viewModels.stateModels.PostResponseState
import com.example.atry.data.remote.RetroApi
import com.example.atry.data.remote.dto.Login
import com.example.atry.viewModels.stateModels.LoginDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val api:RetroApi

):ViewModel(){

    private val _state = mutableStateOf(LoginDataState())
    val state: State<LoginDataState> = _state

    fun login(loginData:Login){
        viewModelScope.launch {
            try {
                _state.value = state.value.copy(isLoading = true)
                _state.value = state.value.copy(
                    loginData = api.login(loginData),
                    isLoading = false
                )

            }
            catch (e:Exception){
                Log.e("LoginViewModel","Login:",e)
                _state.value = state.value.copy(isLoading = false,error=e.toString())

            }
        }

    }

}
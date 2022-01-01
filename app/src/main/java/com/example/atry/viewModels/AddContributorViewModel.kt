package com.example.atry.viewModels
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atry.companyID
import com.example.atry.viewModels.stateModels.PostResponseState
import com.example.atry.data.remote.RetroApi
import com.example.atry.data.remote.dto.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddContributorViewModel @Inject constructor(
    private val api:RetroApi

):ViewModel(){

    private val _state = mutableStateOf(PostResponseState())
    val state: State<PostResponseState> = _state

    fun addContributor(email:String){
        viewModelScope.launch {
            try {
                _state.value = state.value.copy(isLoading = true)
                _state.value = state.value.copy(
                    statusCode = api.addContributor(companyID,email),
                    isLoading = false
                )

            }
            catch (e:Exception){
                Log.e("AddContributorViewModel","addContributor:",e)
                _state.value = state.value.copy(isLoading = false,error="error")

            }
        }

    }

}
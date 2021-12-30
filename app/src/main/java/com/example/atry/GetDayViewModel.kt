package com.example.atry
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atry.data.remote.RetroApi
import com.example.atry.data.remote.dto.DateModel
import com.example.atry.data.remote.dto.Resource
import com.plcoding.ktorclientandroid.data.remote.dto.PostResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetDayViewModel @Inject constructor(
    private val api:RetroApi

):ViewModel(){

    private val _state = mutableStateOf("")
    val state: State<String> = _state

    fun setDay(day:String,dayNum:String){
        viewModelScope.launch {
            try {

                _state.value = "$day $dayNum"


            }
            catch (e:Exception){
                Log.e("AddResourceViewModel","addResources:",e)
                _state.value ="error"

            }
        }

    }

}
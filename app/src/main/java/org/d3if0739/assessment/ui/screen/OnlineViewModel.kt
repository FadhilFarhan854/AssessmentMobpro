package org.d3if0739.assessment.ui.screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.d3if0739.assessment.model.Hewan
import org.d3if0739.assessment.network.ApiStatus
import org.d3if0739.assessment.network.KeuanganApi

class OnlineViewModel : ViewModel() {
    var data = mutableStateOf(emptyList<Hewan>())
        private set

    var status = MutableStateFlow(ApiStatus.LOADING)
        private  set
    init {
        retrieveData()
    }
    fun retrieveData(){
        viewModelScope.launch(Dispatchers.IO){
            try {
                data.value = KeuanganApi.service.getHewan()
                status.value = ApiStatus.SUCCESS
            }catch (e: Exception){
                Log.d("MainViewModel", "Failure: ${e.message}")
                status.value = ApiStatus.FAILED
            }
        }
    }
}
package org.d3if0739.assessment.ui.screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if0739.assessment.model.Hewan
import org.d3if0739.assessment.network.KeuanganApi

class OnlineViewModel : ViewModel() {
    var data = mutableStateOf(emptyList<Hewan>())
        private set
    init {
        retrieveData()
    }
    private fun retrieveData(){
        viewModelScope.launch(Dispatchers.IO){
            try {
                data.value = KeuanganApi.service.getHewan()
            }catch (e: Exception){
                Log.d("MainViewModel", "Failure: ${e.message}")

            }
        }
    }
}
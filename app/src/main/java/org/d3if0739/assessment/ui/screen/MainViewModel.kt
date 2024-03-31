package org.d3if0739.assessment.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.ViewModel
import org.d3if0739.assessment.model.Keuangan

class MainViewModel : ViewModel() {
    val data = mutableListOf<Keuangan>()

    fun addData(tanggal: String, jenis: String, jumlah: String){
        val newData = Keuangan(tanggal, jenis, jumlah)
        data.add(0,newData)
    }

}
package org.d3if0739.assessment.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.d3if0739.assessment.R
import org.d3if0739.assessment.database.KeuanganDao
import org.d3if0739.assessment.model.Keuangan

class MainViewModel(dao: KeuanganDao) : ViewModel() {
    val optionArray : Array<Any> = arrayOf(R.string.pemasukan, R.string.pengeluaran )


    val data: StateFlow<List<Keuangan>> = dao.getMahasiswa().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )


//    fun addData(tanggal: String, jenis: String, jumlah: String){
//        val newData = Keuangan(tanggal, jenis, jumlah)
//        data.add(0,newData)
//    }

    private fun getDataDummy():List<Keuangan>{
        val data = mutableListOf<Keuangan>()
        val uang = arrayOf(
           Keuangan(1,"08052004", "Pemasukan", "500000"),
           Keuangan(2, "08052004", "Pengeluaran", "500000"),
           Keuangan(3, "08052004", "Pemasukan", "500000"),
           Keuangan(4, "08052004", "Pemasukan", "500000"),
           Keuangan(5, "08052004", "Pemasukan", "500000"),
           Keuangan(6, "08052004", "Pemasukan", "500000"),
           Keuangan(7, "08052004", "Pemasukan", "500000"),
           Keuangan(8, "08052004", "Pemasukan", "500000"),
        )

        for (i in uang.indices){
            data.add(uang[i])
        }
        return data
    }
    fun getData(id: Long): Keuangan {
        return data.value.first{it.id == id}
    }

}
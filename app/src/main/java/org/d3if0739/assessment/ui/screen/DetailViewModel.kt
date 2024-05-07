package org.d3if0739.assessment.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if0739.assessment.database.KeuanganDao
import org.d3if0739.assessment.model.Keuangan

class DetailViewModel(private val dao: KeuanganDao) : ViewModel() {

    fun insert(tanggal: String, jumlah: String, jenis: String){
        val keuangan = Keuangan(
            tanggal = tanggal,
            jumlah = jumlah,
            jenis = jenis

        )
        viewModelScope.launch (Dispatchers.IO){
            dao.insert(keuangan)
        }
    }

    fun update(id: Long, tanggal: String, jumlah: String, jenis: String){
        val keuangan = Keuangan(
            id = id,
            tanggal = tanggal,
            jumlah = jumlah,
            jenis = jenis

        )
        viewModelScope.launch (Dispatchers.IO){
            dao.update(keuangan)
        }
    }
    fun delete(id: Long){
        viewModelScope.launch (Dispatchers.IO){
            dao.deletedBYID(id)
        }
    }
    suspend fun getData(id: Long): Keuangan ?{
        return  dao.getMahasiswaById(id)
    }
}

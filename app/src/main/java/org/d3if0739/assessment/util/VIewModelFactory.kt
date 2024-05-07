package org.d3if0739.assessment.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if0739.assessment.database.KeuanganDao
import org.d3if0739.assessment.ui.screen.DetailViewModel
import org.d3if0739.assessment.ui.screen.MainViewModel

class ViewModelFactory(private val dao: KeuanganDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(dao) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(dao) as T
        }
        throw IllegalArgumentException("unknown viewmodel class")
    }

}
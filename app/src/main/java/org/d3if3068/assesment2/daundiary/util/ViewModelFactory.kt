package org.d3if3068.assesment2.daundiary.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if3068.assesment2.daundiary.database.BukuDao
import org.d3if3068.assesment2.daundiary.model.InputViewModel
import org.d3if3068.assesment2.daundiary.model.MainViewModel

class ViewModelFactory(
    private val dao: BukuDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dao) as T
        } else if (modelClass.isAssignableFrom(InputViewModel::class.java)){
            return InputViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
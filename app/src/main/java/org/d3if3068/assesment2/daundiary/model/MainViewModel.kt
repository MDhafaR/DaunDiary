package org.d3if3068.assesment2.daundiary.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.d3if3068.assesment2.daundiary.database.BukuDao

class MainViewModel(dao: BukuDao) : ViewModel() {
    val data: StateFlow<List<DataBuku>> = dao.getBook().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )
}
package org.d3if3068.assesment2.daundiary.model

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3068.assesment2.daundiary.database.BukuDao

class InputViewModel(private val dao: BukuDao) : ViewModel() {
    fun insert(judul: String, deskripsi: String, pengarang:String, warnaBuku: Color, tanggalBuat: Long, tanggalEdit: Long) {
        val buku = DataBuku(
            judul = judul,
            deskripsi = deskripsi,
            pengarang = pengarang,
            warnaBuku = warnaBuku.hashCode(),
            tanggalBuat = tanggalBuat,
            tanggalDiUbah = tanggalEdit
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(buku)
        }
    }

    suspend fun getBook(id: Int): DataBuku? {
        return dao.getBookById(id)
    }

    fun update(id: Int, judul: String, deskripsi: String, pengarang: String, warnaBuku: Color,tanggalBuat: Long, tanggalEdit: Long) {
        val buku = DataBuku(
            id,
            judul,
            deskripsi,
            pengarang,
            warnaBuku.hashCode(),
            tanggalBuat,
            tanggalEdit
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(buku)
        }
    }

    fun delete(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteById(id)
        }
    }
}
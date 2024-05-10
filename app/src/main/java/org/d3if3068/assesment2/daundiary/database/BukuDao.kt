package org.d3if3068.assesment2.daundiary.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.d3if3068.assesment2.daundiary.model.DataBuku

@Dao
interface BukuDao {
    @Insert
    suspend fun insert(dataBuku: DataBuku)

    @Update
    suspend fun update(dataBuku: DataBuku)

    @Query("SELECT * FROM book ORDER BY judul ASC")
    fun getBook(): Flow<List<DataBuku>>

    @Query("SELECT * FROM book WHERE id = :id")
    suspend fun getBookById(id: Int): DataBuku?

    @Query("DELETE FROM book WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT isi FROM book WHERE id = :id")
    fun lihatIsiById(id: Int): Flow<List<String>>

    @Query("SELECT * FROM book WHERE judul LIKE '%' || :searchQuery || '%' ORDER BY judul ASC")
    fun searchBooksByTitle(searchQuery: String): Flow<List<DataBuku>>

    @Query("UPDATE book set judul = :judul, deskripsi = :deskripsi, pengarang = :pengarang, warnaBuku = :warna, tanggalDiUbah = :tanggalUpdate WHERE id = :id")
    suspend fun updateCover(judul: String, deskripsi:String, pengarang: String, warna: Int, tanggalUpdate: Long, id: Int)

    @Query("UPDATE book set isi = :isiNya WHERE id = :id")
    suspend fun updateIsi(isiNya: String, id: Int)
}
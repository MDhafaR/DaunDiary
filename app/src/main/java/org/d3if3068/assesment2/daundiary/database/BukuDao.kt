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

    @Query("SELECT * FROM book WHERE judul LIKE '%' || :searchQuery || '%' ORDER BY judul ASC")
    fun searchBooksByTitle(searchQuery: String): Flow<List<DataBuku>>
}
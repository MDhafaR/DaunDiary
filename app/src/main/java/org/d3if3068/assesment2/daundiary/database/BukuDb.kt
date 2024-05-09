package org.d3if3068.assesment2.daundiary.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.d3if3068.assesment2.daundiary.model.DataBuku

@Database(entities = [DataBuku::class], version = 3, exportSchema = false)
abstract class BukuDb : RoomDatabase() {

    abstract val dao: BukuDao

    companion object {

        @Volatile
        private var INSTANCE: BukuDb? = null

        fun getInstance(context: Context): BukuDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BukuDb::class.java,
                        "book.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
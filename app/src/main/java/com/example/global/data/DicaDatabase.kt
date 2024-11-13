package com.example.global.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.global.model.Dica

@Database(entities = [Dica::class], version = 1)
abstract class DicaDatabase : RoomDatabase() {
    abstract fun dicaDao(): DicaDao

    companion object {
        @Volatile private var instance: DicaDatabase? = null

        fun getDatabase(context: Context): DicaDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, DicaDatabase::class.java, "dicas.db").build()
    }
}

package com.example.global.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.global.model.Dica

@Dao
interface DicaDao {
    @Query("SELECT * FROM dicas")
    fun getAll(): LiveData<List<Dica>>

    @Insert
    suspend fun insert(dica: Dica)

    @Delete
    suspend fun delete(dica: Dica)

    @Update
    suspend fun update(dica: Dica)
}

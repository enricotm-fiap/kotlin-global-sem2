package com.example.global.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.global.model.Dica

class DicaRepository(private val dicaDao: DicaDao) {
    val allDicas: LiveData<List<Dica>> = dicaDao.getAll()

    @WorkerThread
    suspend fun insert(dica: Dica) {
        dicaDao.insert(dica)
    }

    @WorkerThread
    suspend fun delete(dica: Dica) {
        dicaDao.delete(dica)
    }

    @WorkerThread
    suspend fun update(dica: Dica) {
        dicaDao.update(dica)
    }
}

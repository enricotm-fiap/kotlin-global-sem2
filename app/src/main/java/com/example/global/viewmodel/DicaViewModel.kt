package com.example.global.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.global.data.DicaDatabase
import com.example.global.data.DicaRepository
import com.example.global.model.Dica
import kotlinx.coroutines.launch

class DicaViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: DicaRepository
    val allDicas: LiveData<List<Dica>>

    init {
        val dicaDao = DicaDatabase.getDatabase(application).dicaDao()
        repository = DicaRepository(dicaDao)
        allDicas = repository.allDicas
    }

    fun insert(dica: Dica) = viewModelScope.launch {
        repository.insert(dica)
    }

    fun delete(dica: Dica) = viewModelScope.launch {
        repository.delete(dica)
    }

    fun update(dica: Dica) = viewModelScope.launch {
        repository.update(dica)
    }
}

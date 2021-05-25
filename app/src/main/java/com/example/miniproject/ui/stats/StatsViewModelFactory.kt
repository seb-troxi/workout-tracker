package com.example.miniproject.ui.stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.miniproject.database.DataAccessObject

class StatsViewModelFactory(private val database: DataAccessObject) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StatsViewModel::class.java)) {
            return StatsViewModel(database) as T
        }
        throw IllegalArgumentException("ViewModel class fail")
    }
}
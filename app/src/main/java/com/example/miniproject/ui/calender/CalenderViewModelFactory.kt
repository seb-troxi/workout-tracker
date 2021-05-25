package com.example.miniproject.ui.calender

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.miniproject.database.DataAccessObject

class CalenderViewModelFactory(private val database: DataAccessObject) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CalenderViewModel::class.java)) {
            return CalenderViewModel(database) as T
        }
        throw IllegalArgumentException("ViewModel class fail")
    }
}
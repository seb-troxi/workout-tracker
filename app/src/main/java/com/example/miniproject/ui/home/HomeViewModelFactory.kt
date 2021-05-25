package com.example.miniproject.ui.home

import android.view.LayoutInflater
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.miniproject.database.DataAccessObject
import com.example.miniproject.databinding.FragmentHomeBinding

class HomeViewModelFactory(
    private val database: DataAccessObject
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(database) as T
        }
        throw IllegalArgumentException("ViewModel class fail")
    }
}
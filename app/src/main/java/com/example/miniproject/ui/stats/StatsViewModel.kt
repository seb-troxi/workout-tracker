package com.example.miniproject.ui.stats

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.miniproject.database.DataAccessObject
import kotlinx.coroutines.launch

class StatsViewModel(private val database: DataAccessObject) : ViewModel() {
    private val _data = MutableLiveData<Int>()
    val data: LiveData<Int> = _data

    private val _seed = MutableLiveData<String>()
    val seed: LiveData<String> = _seed

    init {
        _seed.value = "gym"
        countEntries()
    }

    private fun countEntries() {
        viewModelScope.launch {
            _data.value = database.getAll()
        }
    }
}
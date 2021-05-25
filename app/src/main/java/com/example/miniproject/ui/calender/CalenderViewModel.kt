package com.example.miniproject.ui.calender

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.miniproject.adapters.CalenderMonthAdapter
import com.example.miniproject.data.calender.DayItem
import com.example.miniproject.database.DataAccessObject
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class CalenderViewModel(private val database: DataAccessObject) : ViewModel() {
    private val _data = MutableLiveData<List<DayItem>>()
    val data: LiveData<List<DayItem>> = _data

    init {
        populateList()
    }

    fun populateList() {
        viewModelScope.launch {
            val dataList = ArrayList<DayItem>()
            val cal = Calendar.getInstance()
            //Fill datalist with hardcoded elements
            for (month in 1..12) {
                dataList.add(
                        DayItem(((month - 1) * 32).toLong(), CalenderMonthAdapter.VIEW_TYPE_MONTH, month, 1, false)
                )
                cal.set(2021, month - 1, 1)
                var daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
                for (day in 1..daysInMonth) {
                    val id: Long = ((month - 1) * 32 + day).toLong()
                    dataList.add(
                            DayItem((id), CalenderMonthAdapter.VIEW_TYPE_DAY, month, day, database.isSelected(id))
                    )
                }
            }
            _data.value = dataList
        }
    }

    fun onDayItemClicked(day: DayItem) {
        day.selected = !day.selected

        var newList = _data.value!!.toMutableList()
        val index = newList.indexOf(day)
        newList[index] = day
        _data.value = newList

        viewModelScope.launch {
            if(day.selected){
                _data.value?.let { database.insert(it[index]) }
            }else{
                _data.value?.let { database.delete(it[index]) }
            }
        }
    }
}
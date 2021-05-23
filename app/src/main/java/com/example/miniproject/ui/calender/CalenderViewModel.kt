package com.example.miniproject.ui.calender

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.miniproject.adapters.CalenderMonthAdapter
import com.example.miniproject.data.calender.Data
import java.util.*
import kotlin.collections.ArrayList

class CalenderViewModel : ViewModel() {
    private val _data = MutableLiveData<List<Data>>().apply {
        val dataList = ArrayList<Data>()
        val cal = Calendar.getInstance()
        //Fill datalist with hardcoded elements
        for (month in 1..12){
            dataList.add(
                Data(CalenderMonthAdapter.VIEW_TYPE_MONTH, month, false)
            )
            cal.set(2021,month-1,1)
            var daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
            for(day in 1..daysInMonth){
                dataList.add(
                    Data(CalenderMonthAdapter.VIEW_TYPE_DAY, day, day%5==0)
                )
            }
        }

        value = dataList
    }
    val data: LiveData<List<Data>> = _data
}
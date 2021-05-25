package com.example.miniproject.ui.home

import android.widget.LinearLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.miniproject.adapters.CalenderMonthAdapter
import com.example.miniproject.adapters.DayItemClickListener
import com.example.miniproject.data.calender.DayItem
import com.example.miniproject.database.DataAccessObject
import com.example.miniproject.databinding.CalenderDayItemBinding
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class HomeViewModel(private val database: DataAccessObject) : ViewModel() {

    private val _data = MutableLiveData<List<DayItem>>()
    val data: LiveData<List<DayItem>> = _data

    init {
        populateList()
    }

    private fun populateList() {
        viewModelScope.launch {
            val dataList = ArrayList<DayItem>()
            val cal = Calendar.getInstance()
            val month = cal.get(Calendar.MONTH)

            //Get first day of THIS week
            var day = cal.get(Calendar.DAY_OF_MONTH) - cal.get(Calendar.DAY_OF_WEEK)

            for(i in 0..6){
                val id: Long = (month*32 + day+i).toLong()
                dataList.add(DayItem(id, CalenderMonthAdapter.VIEW_TYPE_DAY, month, day, database.isSelected(id)))
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
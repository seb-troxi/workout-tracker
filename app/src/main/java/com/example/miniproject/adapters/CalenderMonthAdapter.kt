package com.example.miniproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.miniproject.data.calender.Data
import com.example.miniproject.databinding.CalenderDayItemBinding
import com.example.miniproject.databinding.CalenderMonthItemBinding

class CalenderMonthAdapter :  RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    companion object {
        const val VIEW_TYPE_MONTH = 1
        const val VIEW_TYPE_DAY = 2
    }

    lateinit var list: List<Data>

    private inner class MonthViewHolder(private var binding: CalenderMonthItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.month = list[position].month
            binding.executePendingBindings()
        }
    }

    private inner class DayViewHolder(private var binding: CalenderDayItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.selected = list[position].selected
            binding.executePendingBindings()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_MONTH) {
            return MonthViewHolder(
                CalenderMonthItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }

        return DayViewHolder(
            CalenderDayItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (list[position].viewType == VIEW_TYPE_MONTH) {
            (holder as MonthViewHolder).bind(position)
        } else {
            (holder as DayViewHolder).bind(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].viewType
    }
}


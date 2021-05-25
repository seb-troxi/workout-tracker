package com.example.miniproject.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.miniproject.data.calender.DayItem
import com.example.miniproject.databinding.CalenderDayItemBinding
import com.example.miniproject.databinding.CalenderMonthItemBinding

class CalenderMonthAdapter(private val dayClickListener: DayItemClickListener) : ListAdapter<DayItem, RecyclerView.ViewHolder>(DayItemDiffCallback()){
    companion object {
        const val VIEW_TYPE_MONTH = 1
        const val VIEW_TYPE_DAY = 2
    }

    private val MonthName = listOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")

    private inner class MonthViewHolder(private var binding: CalenderMonthItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(month: DayItem) {
            binding.month = MonthName[month.month-1]
            binding.executePendingBindings()
        }
    }

    private inner class DayViewHolder(private var binding: CalenderDayItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(day: DayItem) {
            binding.clickListener = dayClickListener
            binding.dayItem = day
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

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_MONTH) {
            (holder as MonthViewHolder).bind(getItem(position))
        } else {
            (holder as DayViewHolder).bind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }
}

class DayItemDiffCallback : DiffUtil.ItemCallback<DayItem>() {
    override fun areItemsTheSame(oldItem: DayItem, newItem: DayItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DayItem, newItem: DayItem): Boolean {
        return oldItem.selected == newItem.selected
    }
}

class DayItemClickListener(val clickListener: (day: DayItem) -> Unit) {
    fun onClick(day: DayItem) = clickListener(day)
}

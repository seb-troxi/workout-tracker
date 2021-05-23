//package com.example.miniproject.adapters
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.example.miniproject.data.calender.DayItem
//import com.example.miniproject.databinding.CalenderDayItemBinding
//import com.example.miniproject.databinding.CalenderMonthItemBinding
//
//class CalenderMonthAdapter :  ListAdapter<Int, CalenderMonthAdapter.ViewHolder>(CalenderMonthDiffCallback()){
//    class ViewHolder(private var binding: CalenderMonthItemBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(month: Int) {
//            binding.month = month
//            binding.executePendingBindings()
//        }
//
//        companion object {
//            fun from(parent: ViewGroup) : ViewHolder {
//                val layoutInflater = LayoutInflater.from(parent.context)
//                val binding = CalenderMonthItemBinding.inflate(layoutInflater, parent, false)
//                return ViewHolder(binding)
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder.from(parent)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(getItem(position))
//    }
//}
//
//class CalenderMonthDiffCallback : DiffUtil.ItemCallback<Int>() {
//    override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
//        return oldItem == newItem
//    }
//
//    override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
//        return oldItem == newItem
//    }
//}

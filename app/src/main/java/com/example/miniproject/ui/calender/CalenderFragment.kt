package com.example.miniproject.ui.calender

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.miniproject.adapters.CalenderMonthAdapter
import com.example.miniproject.adapters.DayItemClickListener
import com.example.miniproject.database.DataAccessObject
import com.example.miniproject.database.DayItemDatabase
import com.example.miniproject.databinding.FragmentCalenderBinding
import com.example.miniproject.ui.home.HomeViewModel
import com.example.miniproject.ui.home.HomeViewModelFactory

class CalenderFragment : Fragment() {

    private lateinit var calenderViewModelFactory: CalenderViewModelFactory
    private lateinit var calenderViewModel: CalenderViewModel
    private lateinit var database: DataAccessObject

    private var _binding: FragmentCalenderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        database = DayItemDatabase.getInstance(application).database

        calenderViewModelFactory = CalenderViewModelFactory(database)
        calenderViewModel = ViewModelProvider(this, calenderViewModelFactory).get(CalenderViewModel::class.java)

        // Inflate the layout for this fragment
        _binding = FragmentCalenderBinding.inflate(inflater)

        //Create and Bind Adapter
        val calenderAdapter = CalenderMonthAdapter(
            DayItemClickListener{
                calenderViewModel.onDayItemClicked(it)
            })
        binding.calenderMonthView.adapter = calenderAdapter
        calenderViewModel.data.observe(viewLifecycleOwner, Observer { dayList ->
            dayList?.let {
                calenderAdapter.submitList(dayList)

                //TODO("Automatic Notification")
                calenderAdapter.notifyDataSetChanged()
            }
        })

        //Span count, 1 for the Month Element, 7 for the Day Elements
        val manager = GridLayoutManager(activity, 7)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (calenderAdapter.getItemViewType(position)) {
                    CalenderMonthAdapter.VIEW_TYPE_MONTH -> 7
                    else -> 1
                }
            }
        }
        binding.calenderMonthView.layoutManager = manager

        return binding.root
    }
}
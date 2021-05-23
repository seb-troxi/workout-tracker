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
import com.example.miniproject.databinding.FragmentCalenderBinding

class CalenderFragment : Fragment() {

    private lateinit var calenderViewModel: CalenderViewModel

    private var _binding: FragmentCalenderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        calenderViewModel = ViewModelProvider(this).get(CalenderViewModel::class.java)

        // Inflate the layout for this fragment
        _binding = FragmentCalenderBinding.inflate(inflater)

        //Create and Bind Adapter
        val calenderAdapter = CalenderMonthAdapter()
        binding.calenderMonthView.adapter = calenderAdapter
        calenderViewModel.data.observe(viewLifecycleOwner, Observer {
            calenderAdapter.list = it
        })

        val manager = GridLayoutManager(activity, 7)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (calenderAdapter.list[position].viewType) {
                    CalenderMonthAdapter.VIEW_TYPE_MONTH -> 7
                    else -> 1
                }
            }
        }

        binding.calenderMonthView.layoutManager = manager

        return binding.root
    }
}
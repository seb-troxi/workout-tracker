package com.example.miniproject.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.miniproject.R
import com.example.miniproject.adapters.DayItemClickListener
import com.example.miniproject.data.calender.DayItem
import com.example.miniproject.database.DataAccessObject
import com.example.miniproject.database.DayItemDatabase
import com.example.miniproject.databinding.CalenderDayItemBinding
import com.example.miniproject.databinding.FragmentHomeBinding
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModelFactory: HomeViewModelFactory
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var database: DataAccessObject

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        database = DayItemDatabase.getInstance(application).database

        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater)

        homeViewModelFactory = HomeViewModelFactory(database)
        homeViewModel = ViewModelProvider(this, homeViewModelFactory).get(HomeViewModel::class.java)

        when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)){
            Calendar.MONDAY -> binding.dayMon1.setTextColor(Color.BLACK)
            Calendar.TUESDAY -> binding.dayTue1.setTextColor(Color.BLACK)
            Calendar.WEDNESDAY -> binding.dayWen1.setTextColor(Color.BLACK)
            Calendar.THURSDAY -> binding.dayThu1.setTextColor(Color.BLACK)
            Calendar.FRIDAY -> binding.dayFri1.setTextColor(Color.BLACK)
            Calendar.SATURDAY -> binding.daySat1.setTextColor(Color.BLACK)
            Calendar.SUNDAY -> binding.daySun1.setTextColor(Color.BLACK)
        }

        val params = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1F)
        homeViewModel.data.observe(viewLifecycleOwner, {
            //Inflate the 7 days
            binding.homeView.removeAllViews()
            for(i in 0..6){
                val view = CalenderDayItemBinding.inflate(inflater)
                view.root.layoutParams = params
                view.clickListener = DayItemClickListener{
                    homeViewModel.onDayItemClicked(it)
                }
                view.dayItem = it[i]
                binding.homeView.addView(view.root)
            }
        })

        return binding.root
    }
}
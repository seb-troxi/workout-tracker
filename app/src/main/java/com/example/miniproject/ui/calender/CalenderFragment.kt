package com.example.miniproject.ui.calender

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.miniproject.R

class CalenderFragment : Fragment() {

    private lateinit var calenderViewModel: CalenderViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        calenderViewModel =
                ViewModelProvider(this).get(CalenderViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_calender, container, false)
        val textView: TextView = root.findViewById(R.id.text_calender)
        calenderViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })



        return root
    }
}
package com.example.miniproject.ui.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.miniproject.database.DataAccessObject
import com.example.miniproject.database.DayItemDatabase
import com.example.miniproject.databinding.FragmentStatsBinding

class StatsFragment : Fragment() {

    private lateinit var statsViewModel: StatsViewModel
    private lateinit var statsViewModelFactory: StatsViewModelFactory
    private lateinit var database: DataAccessObject

    private var _binding: FragmentStatsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        database = DayItemDatabase.getInstance(application).database

        statsViewModelFactory = StatsViewModelFactory(database)
        statsViewModel = ViewModelProvider(this, statsViewModelFactory).get(StatsViewModel::class.java)

        // Inflate the layout for this fragment
        _binding = FragmentStatsBinding.inflate(inflater)

        statsViewModel.data.observe(viewLifecycleOwner, {
            binding.selected = it
        })

        var seed: String = "hejs"
        statsViewModel.seed.observe(viewLifecycleOwner, {
            seed = it
        })

        Glide
                .with(this)
                .load("https://picsum.photos/seed/$seed/400/100")
                .into(binding.imageView);

        return binding.root
    }
}
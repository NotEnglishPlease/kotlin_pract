package com.example.kotlin_pract.ui.week

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.kotlin_pract.R
import com.example.kotlin_pract.databinding.FragmentWeekForecastBinding


class WeekForecastFragment : Fragment() {

    private lateinit var binding: FragmentWeekForecastBinding

    private val viewModel: WeekForecastViewModel by viewModels { WeekForecastViewModel.Factory }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentWeekForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.favoriteLocation.observe(viewLifecycleOwner) {
            Log.d("AAA", "onViewCreated: ${it?.name}")
            binding.forecastPlaceholderTextView.text = if (it != null && it.name.isNotEmpty()) {
                getString(R.string.forecast_placeholder, it.name)
            } else {
                getString(R.string.favorite_not_found)
            }
        }
    }
}
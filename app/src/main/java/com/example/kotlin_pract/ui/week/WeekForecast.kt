package com.example.kotlin_pract.ui.week

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.kotlin_pract.R


class WeekForecast : Fragment() {

    private val viewModel: WeekForecastViewModel by viewModels { WeekForecastViewModel.Factory }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_week_forecast, container, false)
    }
}
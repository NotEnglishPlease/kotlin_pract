package com.example.kotlin_pract.ui.week

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
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
        viewModel.favoriteLocation.observe(viewLifecycleOwner) { favoriteCity ->
            if (favoriteCity != null) {
                binding.apply {
                    noFavoriteLocationTextView.isVisible = false
                    cityNameTextView.text = favoriteCity.cityName

                    currentWeatherTextView.text =
                        getString(
                            R.string.weather_description,
                            favoriteCity.weatherDescription.capitalize()
                        )

                    temperatureTextView.text = getString(R.string.temperature, favoriteCity.temp)
                    humidityTextView.text = getString(R.string.humidity, favoriteCity.humidity)
                    pressureTextView.text = getString(R.string.pressure, favoriteCity.pressure)
                }
            } else {
                binding.noFavoriteLocationTextView.isVisible = true
            }
        }
    }
}
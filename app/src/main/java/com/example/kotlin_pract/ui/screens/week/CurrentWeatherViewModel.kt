package com.example.kotlin_pract.ui.screens.week

import androidx.lifecycle.ViewModel
import com.example.kotlin_pract.data.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(repository: WeatherRepository) : ViewModel() {
    val favoriteLocation = repository.favoriteCity
}
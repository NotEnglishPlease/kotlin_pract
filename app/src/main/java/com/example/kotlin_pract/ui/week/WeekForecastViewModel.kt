package com.example.kotlin_pract.ui.week

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_pract.data.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeekForecastViewModel @Inject constructor(repository: WeatherRepository) : ViewModel() {

    val favoriteLocation = repository.favoriteCity

    init {
        viewModelScope.launch {
            repository.updateWeather()
        }
    }
}
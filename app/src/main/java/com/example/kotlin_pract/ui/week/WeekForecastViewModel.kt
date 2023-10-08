package com.example.kotlin_pract.ui.week

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.kotlin_pract.App
import com.example.kotlin_pract.data.WeatherRepository
import kotlinx.coroutines.launch

class WeekForecastViewModel(repository: WeatherRepository) : ViewModel() {

    val favoriteLocation = repository.favoriteCity

    init {
        viewModelScope.launch {
            repository.updateWeather()
        }
    }

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras,
            ): T {
                // Get the Application object from extras
                val application =
                    checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                return WeekForecastViewModel(
                    (application as App).weatherRepository,
                ) as T
            }
        }
    }
}
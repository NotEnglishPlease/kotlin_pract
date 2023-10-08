package com.example.kotlin_pract.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_pract.data.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteLocationsViewModel @Inject constructor(private val repository: WeatherRepository) :
    ViewModel() {

    val cities = repository.cities

    fun setFavoriteCity(city: CityUi) {
        viewModelScope.launch {
            repository.setFavorite(city)
        }
    }
}
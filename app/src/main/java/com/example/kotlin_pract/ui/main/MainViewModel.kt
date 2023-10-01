package com.example.kotlin_pract.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.kotlin_pract.App
import com.example.kotlin_pract.data.City
import com.example.kotlin_pract.data.CityRepository

class MainViewModel(private val repository: CityRepository) : ViewModel() {

    fun addCity(newCityName: String) {
        repository.addCity(city = City(name = newCityName))
    }

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras,
            ): T {
                // Get the Application object from extras
                val application = checkNotNull(extras[APPLICATION_KEY])
                return MainViewModel(
                    (application as App).cityRepository,
                ) as T
            }
        }
    }
}
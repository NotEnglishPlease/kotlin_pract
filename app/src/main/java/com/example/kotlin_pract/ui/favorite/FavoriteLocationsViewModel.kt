package com.example.kotlin_pract.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.kotlin_pract.App
import com.example.kotlin_pract.data.CityRepository

class FavoriteLocationsViewModel(private val repository: CityRepository) : ViewModel() {

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
                return FavoriteLocationsViewModel(
                    (application as App).cityRepository,
                ) as T
            }
        }
    }
}
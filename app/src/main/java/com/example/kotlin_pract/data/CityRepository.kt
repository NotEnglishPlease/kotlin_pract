package com.example.kotlin_pract.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class CityRepository {
    private val _cities = MutableLiveData<List<City>>(emptyList())
    val cities: LiveData<List<City>>
        get() = _cities


    private val _favoriteCity = MutableLiveData<City?>(null)
    val favoriteCity: LiveData<City?>
        get() = _favoriteCity

    fun addCity(city: City) {
        Log.d("AAA", "addCity: $city")
        val oldValue = cities.value?.toMutableList() ?: mutableListOf()
        oldValue.let {
            it.add(city)
            _cities.value = it
            Log.d("AAA", "addCity: ${it.size}")
        }
    }

    fun setFavorite(newFavoriteCity: City) {
        Log.d("AAA", "setFavorite")
        _favoriteCity.value = newFavoriteCity
        val oldList = mutableListOf<City>()
        cities.value?.let { oldList.addAll(it) }
        oldList.forEach { city ->
            city.isFavorite = city.name == newFavoriteCity.name
            if (city.name == newFavoriteCity.name) {
                Log.d("AAA", "setFavorite: ${city.name}")
            }
        }
        _cities.value = oldList
    }
}

package com.example.kotlin_pract.data

class CityRepository {
    val cities = mutableListOf<City>()
    var favoriteCity: City? = null
    fun addCity(city: City) = cities.add(city)
    fun setFavorite(city: City) {
        favoriteCity = city
    }
}
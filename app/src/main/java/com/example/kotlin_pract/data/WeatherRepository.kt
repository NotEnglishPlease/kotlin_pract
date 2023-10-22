package com.example.kotlin_pract.data

import android.util.Log
import com.example.kotlin_pract.data.api.OpenWeatherMapApiService
import com.example.kotlin_pract.data.api.models.toEntity
import com.example.kotlin_pract.data.db.WeatherDao
import com.example.kotlin_pract.data.db.WeatherEntity
import com.example.kotlin_pract.ui.favorite.CityUiState
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val openWeatherMapApiService: OpenWeatherMapApiService,
    private val weatherDao: WeatherDao,
) {
    val cities = weatherDao.getAllWeatherEntities()
    val favoriteCity = weatherDao.getFavoriteCityWeatherLiveData()

    suspend fun addNewCity(name: String) {
        weatherDao.upsertWeather(
            weatherEntity = openWeatherMapApiService.getCurrentWeather(name).toEntity()
        )
    }

    suspend fun updateWeather() {
        val oldData = weatherDao.getFavoriteCityWeather()
        if (oldData != null) {
            val newData = openWeatherMapApiService.getCurrentWeather(oldData.cityName).toEntity()
            val updatedData = WeatherEntity(
                id = oldData.id,
                temp = newData.temp,
                humidity = newData.humidity,
                pressure = newData.pressure,
                isFavorite = oldData.isFavorite,
                cityName = oldData.cityName,
                weatherDescription = newData.weatherDescription
            )
            weatherDao.upsertWeather(updatedData)
        }
    }

    suspend fun setFavorite(city: CityUiState) {
        val oldFavorite = weatherDao.getFavoriteCityWeather()
        oldFavorite?.let {
            weatherDao.upsertWeather(it.copy(isFavorite = false))
        }

        Log.d("DDD", "setFavorite: $oldFavorite")
        val oldValue = weatherDao.getWeatherById(id = city.id)
        val newValue = oldValue.copy(isFavorite = true)
        weatherDao.upsertWeather(weatherEntity = newValue)
    }
}

package com.example.kotlin_pract.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather")
    fun getAllWeatherEntities(): LiveData<List<WeatherEntity>>

    @Query("SELECT * FROM weather WHERE is_favorite")
    fun getFavoriteCityWeatherLiveData(): LiveData<WeatherEntity>

    @Query("SELECT * FROM weather WHERE is_favorite")
    suspend fun getFavoriteCityWeather(): WeatherEntity?

    @Upsert
    suspend fun upsertWeather(weatherEntity: WeatherEntity)

    @Query("SELECT * FROM weather WHERE id = :id")
    suspend fun getWeatherById(id: Int): WeatherEntity
}
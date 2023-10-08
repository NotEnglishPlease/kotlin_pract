package com.example.kotlin_pract.data.api

import com.example.kotlin_pract.data.api.models.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "16000eaca383543a16ba015da409de62"

interface OpenWeatherMapApiService {
    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("q") cityName: String,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String = API_KEY,
        @Query("lang") language: String = "ru",
    ): WeatherResponse
}

package com.example.kotlin_pract.data.api.models

import com.squareup.moshi.Json

data class WeatherMainInfo(
    @Json(name = "temp") val temperature: Double,
    @Json(name = "feels_like") val feelsLike: Double,
    @Json(name = "temp_min") val minTemperature: Double,
    @Json(name = "temp_max") val maxTemperature: Double,
    @Json(name = "pressure") val pressure: Int,
    @Json(name = "humidity") val humidity: Int,
)
package com.example.kotlin_pract.data.api.models

import com.squareup.moshi.Json

data class WeatherDescription(
    @Json(name = "id") val id: Int,
    @Json(name = "main") val main: String,
    @Json(name = "description") val description: String,
    @Json(name = "icon") val icon: String,
)
package com.example.kotlin_pract.data.api.models

import com.example.kotlin_pract.data.db.WeatherEntity
import com.squareup.moshi.Json

data class WeatherResponse(
    @Json(name = "weather") val weatherDescriptions: List<WeatherDescription>,
    @Json(name = "base") val dataProvider: String,
    @Json(name = "main") val mainInfo: WeatherMainInfo,
    @Json(name = "visibility") val visibility: Int,
    @Json(name = "dt") val dateTime: Long,
    @Json(name = "sys") val sysInfo: SysInfo,
    @Json(name = "timezone") val timeZoneOffset: Int,
    @Json(name = "id") val cityId: Int,
    @Json(name = "name") val cityName: String,
    @Json(name = "cod") val responseCode: Int,
)

fun WeatherResponse.toEntity(): WeatherEntity {
    return WeatherEntity(
        id = cityId,
        cityName = cityName,
        weatherDescription = weatherDescriptions.first().description,
        temp = mainInfo.temperature,
        humidity = mainInfo.humidity,
        pressure = mainInfo.pressure,
        isFavorite = false
    )
}


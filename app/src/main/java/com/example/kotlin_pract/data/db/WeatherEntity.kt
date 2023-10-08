package com.example.kotlin_pract.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kotlin_pract.ui.favorite.CityUi

@Entity(tableName = "weather")
data class WeatherEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "city_name") val cityName: String,
    @ColumnInfo(name = "weather_description") val weatherDescription: String,
    @ColumnInfo(name = "temp") val temp: Double,
    @ColumnInfo(name = "pressure") val pressure: Int,
    @ColumnInfo(name = "humidity") val humidity: Int,
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean,
)

fun WeatherEntity.toCityUi(): CityUi {
    return CityUi(
        id = id,
        name = cityName,
        isFavorite = isFavorite
    )
}



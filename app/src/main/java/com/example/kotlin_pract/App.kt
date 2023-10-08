package com.example.kotlin_pract

import android.app.Application
import com.example.kotlin_pract.data.WeatherRepository
import com.example.kotlin_pract.data.api.OpenWeatherMapApiService
import com.example.kotlin_pract.data.db.WeatherDatabase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class App : Application() {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl("https://api.openweathermap.org/")
        .build()


    private val openWeatherMapApiService: OpenWeatherMapApiService by lazy {
        retrofit.create(OpenWeatherMapApiService::class.java)
    }

    private val db by lazy { WeatherDatabase.getDatabase(this) }

    private val weatherDao by lazy { db.weatherDao() }
    val weatherRepository by lazy {
        WeatherRepository(
            openWeatherMapApiService,
            weatherDao
        )
    }
}
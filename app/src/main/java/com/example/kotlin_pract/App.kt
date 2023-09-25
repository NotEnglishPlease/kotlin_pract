package com.example.kotlin_pract

import android.app.Application
import com.example.kotlin_pract.data.CityRepository

class App : Application() {
    val cityRepository = CityRepository()
}
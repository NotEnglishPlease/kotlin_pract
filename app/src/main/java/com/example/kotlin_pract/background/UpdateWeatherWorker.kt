package com.example.kotlin_pract.background

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.kotlin_pract.data.WeatherRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@HiltWorker
class UpdateWeatherWorker @AssistedInject constructor(
    @Assisted private val weatherRepository: WeatherRepository,
    @Assisted val appContext: Context,
    @Assisted val params: WorkerParameters
) : CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            try {
                println("doWork: working")
                weatherRepository.updateWeather()
                Result.success()
            } catch (e: Exception) {
                Result.failure()
            }
        }
    }
}
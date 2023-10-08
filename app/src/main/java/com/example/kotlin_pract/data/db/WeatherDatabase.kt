package com.example.kotlin_pract.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WeatherEntity::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao

    companion object {
        @Volatile
        private var INSTANCE: WeatherDatabase? = null
        fun getDatabase(context: Context): WeatherDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = INSTANCE
                if (instance == null) {
                    val newInstance = Room.databaseBuilder(
                        context.applicationContext,
                        WeatherDatabase::class.java,
                        "weather"
                    ).build()
                    INSTANCE = newInstance
                    newInstance
                } else {
                    instance
                }
            }
        }
    }
}
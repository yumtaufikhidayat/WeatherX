package com.taufik.weatherx.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.taufik.weatherx.data.local.dao.WeatherXDao
import com.taufik.weatherx.data.local.entity.WeatherEntity

@Database(
    entities = [WeatherEntity::class],
    version = 1,
    exportSchema = false
)
abstract class WeatherXDatabase : RoomDatabase() {
    abstract fun getWeatherXDao(): WeatherXDao
}
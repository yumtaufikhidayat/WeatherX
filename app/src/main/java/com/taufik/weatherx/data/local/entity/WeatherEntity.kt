package com.taufik.weatherx.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_weather")
data class WeatherEntity(
    @PrimaryKey
    var id: Int = 0,
    var cityName: String = "",
    var weatherIcon: String = "",
    var weatherDegree: Double = 0.0,
    var lat: Double = 0.0,
    var lon: Double = 0.0
)
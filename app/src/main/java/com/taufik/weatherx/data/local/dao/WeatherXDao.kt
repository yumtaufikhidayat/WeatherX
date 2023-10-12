package com.taufik.weatherx.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.taufik.weatherx.data.local.entity.WeatherEntity

@Dao
interface WeatherXDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCityWeather(weather: WeatherEntity): Long

    @Query("SELECT * FROM tbl_weather ORDER BY cityName DESC")
    fun getAllSavedCityWeather(): LiveData<List<WeatherEntity>>

    @Query("SELECT count(*) FROM tbl_weather WHERE id = :id")
    fun checkSavedCityWeather(id: Int): LiveData<Int>

    @Query("DELETE FROM tbl_weather WHERE id = :id")
    fun removeSavedCityWeather(id: Int): Int
}
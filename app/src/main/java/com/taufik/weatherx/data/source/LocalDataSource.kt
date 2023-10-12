package com.taufik.weatherx.data.source

import com.taufik.weatherx.data.local.dao.WeatherXDao
import com.taufik.weatherx.data.local.entity.WeatherEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao: WeatherXDao
) {
    fun saveCityWeather(weather: WeatherEntity) = dao.saveCityWeather(weather)

    fun getAllSavedCityWeather() = dao.getAllSavedCityWeather()

    fun checkSavedCityWeather(id: Int) = dao.checkSavedCityWeather(id)

    fun removeSavedCityWeather(id: Int) = dao.removeSavedCityWeather(id)
}
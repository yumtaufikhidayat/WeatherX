package com.taufik.weatherx.data.repository

import com.taufik.weatherx.data.local.entity.WeatherEntity
import com.taufik.weatherx.data.source.LocalDataSource
import com.taufik.weatherx.data.source.RemoteDataSource
import javax.inject.Inject

class WeatherXRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {
    fun getCityName(query: String) = remoteDataSource.getCityName(query)

    fun getCityWeather(
        lat: Double,
        lon: Double,
        units: String
    ) = remoteDataSource.getCityWeather(lat, lon, units)

    fun getCityWeathersHourly(
        lat: Double,
        lon: Double,
        exclude: String,
        units: String
    ) = remoteDataSource.getCityWeathersHourly(lat, lon, exclude, units)

    fun getCityWeathersDaily(
        lat: Double,
        lon: Double,
        units: String
    ) = remoteDataSource.getCityWeathersDaily(lat, lon, units)

    fun saveCityWeather(weather: WeatherEntity) = localDataSource.saveCityWeather(weather)

    fun getAllSavedCityWeather() = localDataSource.getAllSavedCityWeather()

    fun checkSavedCityWeather(id: Int) = localDataSource.checkSavedCityWeather(id)

    fun removeSavedCityWeather(id: Int) = localDataSource.removeSavedCityWeather(id)
}
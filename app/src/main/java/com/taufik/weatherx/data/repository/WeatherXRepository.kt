package com.taufik.weatherx.data.repository

import com.taufik.weatherx.data.source.RemoteDataSource
import javax.inject.Inject

class WeatherXRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    fun getCityName(query: String) = remoteDataSource.getCityName(query)
}
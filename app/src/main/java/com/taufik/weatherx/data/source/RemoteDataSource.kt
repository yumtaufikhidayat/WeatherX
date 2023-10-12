package com.taufik.weatherx.data.source

import com.taufik.weatherx.BuildConfig
import com.taufik.weatherx.data.BaseApiResponse
import com.taufik.weatherx.data.NetworkResult
import com.taufik.weatherx.data.remote.service.ApiService
import com.taufik.weatherx.model.details.daily.WeatherDailyResponse
import com.taufik.weatherx.model.details.onecall.OneCallWeatherResponse
import com.taufik.weatherx.model.details.weather.DetailWeathersResponse
import com.taufik.weatherx.model.search.SearchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) : BaseApiResponse() {

    private val dispatchersIO = Dispatchers.IO
    private val appId = BuildConfig.APP_ID

    fun getCityName(query: String): Flow<NetworkResult<SearchResponse>> {
        return flow {
            emit(safeApiCall { apiService.getCityName(query, appId) })
        }.flowOn(dispatchersIO)
    }

    fun getCityWeather(
        lat: Double,
        lon: Double,
        units: String
    ): Flow<NetworkResult<DetailWeathersResponse>> {
        return flow {
            emit(safeApiCall {
                apiService.getCityWeather(
                    lat,
                    lon,
                    units,
                    appId
                )
            })
        }.flowOn(dispatchersIO)
    }

    fun getCityWeathersHourly(
        lat: Double,
        lon: Double,
        exclude: String,
        units: String
    ): Flow<NetworkResult<OneCallWeatherResponse>> {
        return flow {
            emit(safeApiCall {
                apiService.getCityWeathersHourly(
                    lat,
                    lon,
                    exclude,
                    units,
                    appId
                )
            })
        }.flowOn(dispatchersIO)
    }

    fun getCityWeathersDaily(
        lat: Double,
        lon: Double,
        units: String
    ): Flow<NetworkResult<WeatherDailyResponse>> {
        return flow {
            emit(safeApiCall {
                apiService.getCityWeathersDaily(
                    lat,
                    lon,
                    units,
                    appId
                )
            })
        }.flowOn(dispatchersIO)
    }
}
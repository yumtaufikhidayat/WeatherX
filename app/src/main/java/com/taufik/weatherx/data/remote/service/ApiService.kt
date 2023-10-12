package com.taufik.weatherx.data.remote.service

import com.taufik.weatherx.model.details.daily.WeatherDailyResponse
import com.taufik.weatherx.model.details.onecall.OneCallWeatherResponse
import com.taufik.weatherx.model.details.weather.DetailWeathersResponse
import com.taufik.weatherx.model.search.SearchResponse
import com.taufik.weatherx.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(UrlEndpoint.PARAM_DIRECT)
    suspend fun getCityName(
        @Query(Constants.QUERY_Q) query: String,
        @Query(Constants.QUERY_APP_ID) appId: String
    ): Response<SearchResponse>

    @GET(UrlEndpoint.PARAM_WEATHER)
    suspend fun getCityWeather(
        @Query(Constants.QUERY_LAT) lat: Double,
        @Query(Constants.QUERY_LON) lon: Double,
        @Query(Constants.QUERY_UNITS) units: String,
        @Query(Constants.QUERY_APP_ID) appId: String
    ): Response<DetailWeathersResponse>

    @GET(UrlEndpoint.PARAM_ONECALL)
    suspend fun getCityWeathersHourly(
        @Query(Constants.QUERY_LAT) lat: Double,
        @Query(Constants.QUERY_LON) lon: Double,
        @Query(Constants.QUERY_EXCLUDE) exclude: String,
        @Query(Constants.QUERY_UNITS) units: String,
        @Query(Constants.QUERY_APP_ID) appId: String
    ): Response<OneCallWeatherResponse>

    @GET(UrlEndpoint.PARAM_ONECALL)
    suspend fun getCityWeathersDaily(
        @Query(Constants.QUERY_LAT) lat: Double,
        @Query(Constants.QUERY_LON) lon: Double,
        @Query(Constants.QUERY_UNITS) units: String,
        @Query(Constants.QUERY_APP_ID) appId: String
    ): Response<WeatherDailyResponse>

}
package com.taufik.weatherx.data.remote.service

import com.taufik.weatherx.model.search.SearchResponse
import com.taufik.weatherx.model.search.SearchResponseItem
import com.taufik.weatherx.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
//    http://api.openweathermap.org/geo/1.0/direct?q=Medan&appid=6a7431e5ec8999b74875f8d7252f1477
    @GET(UrlEndpoint.PARAM_DIRECT)
    suspend fun getCityName(
        @Query(Constants.QUERY_Q) query: String,
        @Query(Constants.QUERY_APP_ID) appId: String
    ): Response<SearchResponse>
}
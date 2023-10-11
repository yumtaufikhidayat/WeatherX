package com.taufik.weatherx.data.source

import com.taufik.weatherx.BuildConfig
import com.taufik.weatherx.data.BaseApiResponse
import com.taufik.weatherx.data.NetworkResult
import com.taufik.weatherx.data.remote.service.ApiService
import com.taufik.weatherx.model.search.SearchResponse
import com.taufik.weatherx.model.search.SearchResponseItem
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
}
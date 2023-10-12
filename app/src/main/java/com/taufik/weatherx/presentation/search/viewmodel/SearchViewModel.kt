package com.taufik.weatherx.presentation.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taufik.weatherx.data.NetworkResult
import com.taufik.weatherx.data.repository.WeatherXRepository
import com.taufik.weatherx.model.search.SearchResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: WeatherXRepository
) : ViewModel() {

    private val _searchCity: MutableLiveData<NetworkResult<SearchResponse>> = MutableLiveData()
    val searchCity: LiveData<NetworkResult<SearchResponse>> = _searchCity

    fun getCityName(query: String) = viewModelScope.launch {
        repository.getCityName(query).collect {
            _searchCity.value = it
        }
    }
}
package com.taufik.weatherx.presentation.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taufik.weatherx.data.NetworkResult
import com.taufik.weatherx.data.repository.WeatherXRepository
import com.taufik.weatherx.model.details.daily.WeatherDailyResponse
import com.taufik.weatherx.model.details.onecall.OneCallWeatherResponse
import com.taufik.weatherx.model.details.weather.DetailWeathersResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: WeatherXRepository
) : ViewModel() {

    private val _cityWeather: MutableLiveData<NetworkResult<DetailWeathersResponse>> =
        MutableLiveData()
    val cityWeather: LiveData<NetworkResult<DetailWeathersResponse>> = _cityWeather

    private val _cityWeatherHourly: MutableLiveData<NetworkResult<OneCallWeatherResponse>> =
        MutableLiveData()
    val cityWeatherHourly: LiveData<NetworkResult<OneCallWeatherResponse>> = _cityWeatherHourly

    private val _cityWeatherDaily: MutableLiveData<NetworkResult<WeatherDailyResponse>> =
        MutableLiveData()
    val cityWeatherDaily: LiveData<NetworkResult<WeatherDailyResponse>> = _cityWeatherDaily

    fun getCityWeather(
        lat: Double,
        lon: Double,
        units: String
    ) = viewModelScope.launch {
        repository.getCityWeather(lat, lon, units).collect {
            _cityWeather.value = it
        }
    }

    fun getCityWeathersHourly(
        lat: Double,
        lon: Double,
        exclude: String,
        units: String
    ) = viewModelScope.launch {
        repository.getCityWeathersHourly(lat, lon, exclude, units).collect {
            _cityWeatherHourly.value = it
        }
    }

    fun getCityWeathersDaily(
        lat: Double,
        lon: Double,
        units: String
    ) = viewModelScope.launch {
        repository.getCityWeathersDaily(lat, lon, units).collect {
            _cityWeatherDaily.value = it
        }
    }
}
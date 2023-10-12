package com.taufik.weatherx.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import com.taufik.weatherx.data.repository.WeatherXRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: WeatherXRepository
) : ViewModel() {
    fun getAllSavedCityWeather() = repository.getAllSavedCityWeather()
}
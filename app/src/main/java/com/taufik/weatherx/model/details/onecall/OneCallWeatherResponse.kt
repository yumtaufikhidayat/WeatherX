package com.taufik.weatherx.model.details.onecall


import com.google.gson.annotations.SerializedName

data class OneCallWeatherResponse(
    @SerializedName("current")
    val current: Current = Current(),
    @SerializedName("daily")
    val daily: List<Daily> = listOf(),
    @SerializedName("hourly")
    val hourly: List<Hourly> = listOf(),
    @SerializedName("lat")
    val lat: Double = 0.0, // 33.44
    @SerializedName("lon")
    val lon: Double = 0.0, // -94.04
    @SerializedName("minutely")
    val minutely: List<Minutely> = listOf(),
    @SerializedName("timezone")
    val timezone: String = "", // America/Chicago
    @SerializedName("timezone_offset")
    val timezoneOffset: Int = 0 // -18000
)
package com.taufik.weatherx.model.details.onecall


import com.google.gson.annotations.SerializedName

data class Hourly(
    @SerializedName("clouds")
    val clouds: Int = 0, // 0
    @SerializedName("dew_point")
    val dewPoint: Double = 0.0, // 15.76
    @SerializedName("dt")
    val dt: Long = 0L, // 1697014800
    @SerializedName("feels_like")
    val feelsLike: Double = 0.0, // 18.61
    @SerializedName("humidity")
    val humidity: Int = 0, // 84
    @SerializedName("pop")
    val pop: Double = 0.0, // 0
    @SerializedName("pressure")
    val pressure: Int = 0, // 1008
    @SerializedName("temp")
    val temp: Double = 0.0, // 18.51
    @SerializedName("uvi")
    val uvi: Double = 0.0, // 0.35
    @SerializedName("visibility")
    val visibility: Int = 0, // 10000
    @SerializedName("weather")
    val weather: List<Weather> = listOf(),
    @SerializedName("wind_deg")
    val windDeg: Int = 0, // 181
    @SerializedName("wind_gust")
    val windGust: Double = 0.0, // 5.87
    @SerializedName("wind_speed")
    val windSpeed: Double = 0.0 // 2.61
)
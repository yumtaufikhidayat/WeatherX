package com.taufik.weatherx.model.details.onecall


import com.google.gson.annotations.SerializedName

data class Current(
    @SerializedName("clouds")
    val clouds: Int = 0, // 0
    @SerializedName("dew_point")
    val dewPoint: Double = 0.0, // 15.76
    @SerializedName("dt")
    val dt: Int = 0, // 1697016583
    @SerializedName("feels_like")
    val feelsLike: Double = 0.0, // 18.61
    @SerializedName("humidity")
    val humidity: Int = 0, // 84
    @SerializedName("pressure")
    val pressure: Int = 0, // 1008
    @SerializedName("sunrise")
    val sunrise: Int = 0, // 1697026642
    @SerializedName("sunset")
    val sunset: Int = 0, // 1697068092
    @SerializedName("temp")
    val temp: Double = 0.0, // 18.51
    @SerializedName("uvi")
    val uvi: Double = 0.0, // 0
    @SerializedName("visibility")
    val visibility: Int = 0, // 10000
    @SerializedName("weather")
    val weather: List<Weather> = listOf(),
    @SerializedName("wind_deg")
    val windDeg: Int = 0, // 190
    @SerializedName("wind_speed")
    val windSpeed: Double = 0.0 // 2.57
)
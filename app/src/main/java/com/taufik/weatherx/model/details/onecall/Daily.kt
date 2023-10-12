package com.taufik.weatherx.model.details.onecall


import com.google.gson.annotations.SerializedName

data class Daily(
    @SerializedName("clouds")
    val clouds: Int = 0, // 100
    @SerializedName("dew_point")
    val dewPoint: Double = 0.0, // 14.2
    @SerializedName("dt")
    val dt: Int = 0, // 1697047200
    @SerializedName("feels_like")
    val feelsLike: FeelsLike = FeelsLike(),
    @SerializedName("humidity")
    val humidity: Int = 0, // 52
    @SerializedName("moon_phase")
    val moonPhase: Double = 0.0, // 0.91
    @SerializedName("moonrise")
    val moonrise: Int = 0, // 1697016120
    @SerializedName("moonset")
    val moonset: Int = 0, // 1697063640
    @SerializedName("pop")
    val pop: Double = 0.0, // 0.04
    @SerializedName("pressure")
    val pressure: Int = 0, // 1009
    @SerializedName("sunrise")
    val sunrise: Int = 0, // 1697026642
    @SerializedName("sunset")
    val sunset: Int = 0, // 1697068092
    @SerializedName("temp")
    val temp: Temp = Temp(),
    @SerializedName("uvi")
    val uvi: Double = 0.0, // 5.6
    @SerializedName("weather")
    val weather: List<Weather> = listOf(),
    @SerializedName("wind_deg")
    val windDeg: Int = 0, // 165
    @SerializedName("wind_gust")
    val windGust: Double = 0.0, // 10.25
    @SerializedName("wind_speed")
    val windSpeed: Double = 0.0 // 6.34
)
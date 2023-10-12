package com.taufik.weatherx.model.details.weather


import com.google.gson.annotations.SerializedName

data class Wind(
    @SerializedName("deg")
    val deg: Int = 0, // 206
    @SerializedName("gust")
    val gust: Double = 0.0, // 10.68
    @SerializedName("speed")
    val speed: Double = 0.0 // 6.54
)
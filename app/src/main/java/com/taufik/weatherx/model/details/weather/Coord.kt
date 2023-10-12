package com.taufik.weatherx.model.details.weather


import com.google.gson.annotations.SerializedName

data class Coord(
    @SerializedName("lat")
    val lat: Double = 0.0, // 53.2404
    @SerializedName("lon")
    val lon: Double = 0.0 // 30.0346
)
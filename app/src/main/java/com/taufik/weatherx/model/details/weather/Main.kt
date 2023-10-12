package com.taufik.weatherx.model.details.weather


import com.google.gson.annotations.SerializedName

data class Main(
    @SerializedName("feels_like")
    val feelsLike: Double = 0.0, // 6.83
    @SerializedName("grnd_level")
    val grndLevel: Int = 0, // 1004
    @SerializedName("humidity")
    val humidity: Int = 0, // 47
    @SerializedName("pressure")
    val pressure: Int = 0, // 1022
    @SerializedName("sea_level")
    val seaLevel: Int = 0, // 1022
    @SerializedName("temp")
    val temp: Double = 0.0, // 9.81
    @SerializedName("temp_max")
    val tempMax: Double = 0.0, // 9.81
    @SerializedName("temp_min")
    val tempMin: Double = 0.0 // 9.81
)
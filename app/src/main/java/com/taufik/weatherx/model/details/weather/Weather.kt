package com.taufik.weatherx.model.details.weather


import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("description")
    val description: String = "", // overcast clouds
    @SerializedName("icon")
    val icon: String = "", // 04d
    @SerializedName("id")
    val id: Int = 0, // 804
    @SerializedName("main")
    val main: String = "" // Clouds
)
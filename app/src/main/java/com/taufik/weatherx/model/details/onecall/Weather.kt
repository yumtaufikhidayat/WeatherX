package com.taufik.weatherx.model.details.onecall


import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("description")
    val description: String = "", // clear sky
    @SerializedName("icon")
    val icon: String = "", // 01n
    @SerializedName("id")
    val id: Int = 0, // 800
    @SerializedName("main")
    val main: String = "" // Clear
)
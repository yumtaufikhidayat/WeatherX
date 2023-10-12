package com.taufik.weatherx.model.details.weather


import com.google.gson.annotations.SerializedName

data class Sys(
    @SerializedName("country")
    val country: String = "", // BY
    @SerializedName("sunrise")
    val sunrise: Int = 0, // 1696997882
    @SerializedName("sunset")
    val sunset: Int = 0 // 1697037306
)
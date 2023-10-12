package com.taufik.weatherx.model.details.weather


import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all")
    val all: Int = 0 // 95
)
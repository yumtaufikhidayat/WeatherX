package com.taufik.weatherx.model.details.onecall


import com.google.gson.annotations.SerializedName

data class FeelsLike(
    @SerializedName("day")
    val day: Double = 0.0, // 24.3
    @SerializedName("eve")
    val eve: Double = 0.0, // 19.51
    @SerializedName("morn")
    val morn: Double = 0.0, // 18.05
    @SerializedName("night")
    val night: Double = 0.0 // 15.13
)
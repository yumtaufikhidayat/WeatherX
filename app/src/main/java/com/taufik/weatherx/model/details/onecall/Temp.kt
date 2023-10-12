package com.taufik.weatherx.model.details.onecall


import com.google.gson.annotations.SerializedName

data class Temp(
    @SerializedName("day")
    val day: Double = 0.0, // 24.44
    @SerializedName("eve")
    val eve: Double = 0.0, // 19.83
    @SerializedName("max")
    val max: Double = 0.0, // 24.93
    @SerializedName("min")
    val min: Double = 0.0, // 15.44
    @SerializedName("morn")
    val morn: Double = 0.0, // 18.14
    @SerializedName("night")
    val night: Double = 0.0 // 15.44
)
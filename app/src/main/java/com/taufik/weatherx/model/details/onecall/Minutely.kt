package com.taufik.weatherx.model.details.onecall


import com.google.gson.annotations.SerializedName

data class Minutely(
    @SerializedName("dt")
    val dt: Int = 0, // 1697016600
    @SerializedName("precipitation")
    val precipitation: Int = 0 // 0
)
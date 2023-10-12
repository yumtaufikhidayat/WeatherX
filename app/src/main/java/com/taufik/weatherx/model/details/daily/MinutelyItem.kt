package com.taufik.weatherx.model.details.daily

import com.google.gson.annotations.SerializedName


data class MinutelyItem(
    @SerializedName("dt")
    val dt: Int = 0,

    @SerializedName("precipitation")
    val precipitation: Double = 0.0
)

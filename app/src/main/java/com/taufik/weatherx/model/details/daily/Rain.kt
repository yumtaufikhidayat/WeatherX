package com.taufik.weatherx.model.details.daily

import com.google.gson.annotations.SerializedName


data class Rain(
    @SerializedName("1h")
    val jsonMember1h: Double = 0.0
)

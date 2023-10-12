package com.taufik.weatherx.model.details.daily

import com.google.gson.annotations.SerializedName


class WeatherDailyResponse {
    @SerializedName("timezone")
    val timezone: String? = null

    @SerializedName("timezone_offset")
    val timezoneOffset = 0

    @SerializedName("daily")
    val daily: List<DailyItem>? = null

    @SerializedName("lon")
    val lon = 0.0

    @SerializedName("hourly")
    val hourly: List<HourlyItem>? = null

    @SerializedName("minutely")
    val minutely: List<MinutelyItem>? = null

    @SerializedName("lat")
    val lat = 0.0
}

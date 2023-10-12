package com.taufik.weatherx.model.details.weather


import com.google.gson.annotations.SerializedName

data class DetailWeathersResponse(
    @SerializedName("base")
    val base: String = "", // stations
    @SerializedName("clouds")
    val clouds: Clouds = Clouds(),
    @SerializedName("cod")
    val cod: Int = 0, // 200
    @SerializedName("coord")
    val coord: Coord = Coord(),
    @SerializedName("dt")
    val dt: Int = 0, // 1697013015
    @SerializedName("id")
    val id: Int = 0, // 622739
    @SerializedName("main")
    val main: Main = Main(),
    @SerializedName("name")
    val name: String = "", // Rahachow
    @SerializedName("sys")
    val sys: Sys = Sys(),
    @SerializedName("timezone")
    val timezone: Int = 0, // 10800
    @SerializedName("visibility")
    val visibility: Int = 0, // 10000
    @SerializedName("weather")
    val weather: List<Weather> = listOf(),
    @SerializedName("wind")
    val wind: Wind = Wind()
)
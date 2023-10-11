package com.taufik.weatherx.model.search


import com.google.gson.annotations.SerializedName

data class SearchResponseItem(
    @SerializedName("country")
    val country: String = "", // ID
    @SerializedName("lat")
    val lat: Double = 0.0, // 3.5896654
    @SerializedName("local_names")
    val localNames: LocalNames = LocalNames(),
    @SerializedName("lon")
    val lon: Double = 0.0, // 98.6738261
    @SerializedName("name")
    val name: String = "", // City of Medan
    @SerializedName("state")
    val state: String = "" // North Sumatra
)
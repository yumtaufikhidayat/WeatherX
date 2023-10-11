package com.taufik.weatherx.model.search


import com.google.gson.annotations.SerializedName

data class LocalNames(
    @SerializedName("en")
    val en: String = "", // City of Medan
    @SerializedName("hi")
    val hi: String = "", // मॆदान्
    @SerializedName("id")
    val id: String = "", // Kota Medan
    @SerializedName("ja")
    val ja: String = "", // メダン
    @SerializedName("kn")
    val kn: String = "", // ಮೆದಾನ್
    @SerializedName("lt")
    val lt: String = "", // Medanas
    @SerializedName("oc")
    val oc: String = "", // Medan
    @SerializedName("pt")
    val pt: String = "", // Medan
    @SerializedName("ru")
    val ru: String = "", // Медан
    @SerializedName("uk")
    val uk: String = "", // Медан
    @SerializedName("zh")
    val zh: String = "" // 棉兰
)
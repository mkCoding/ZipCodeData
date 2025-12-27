package com.example.zipcodeinfo.data.model2


import com.google.gson.annotations.SerializedName

data class ModelPlace(
    @SerializedName("latitude")
    val latitude: String? = "",
    @SerializedName("longitude")
    val longitude: String? = "",
    @SerializedName("place name")
    val placeName: String? = "",
    @SerializedName("state")
    val state: String? = "",
    @SerializedName("state abbreviation")
    val stateAbbreviation: String? = ""
)
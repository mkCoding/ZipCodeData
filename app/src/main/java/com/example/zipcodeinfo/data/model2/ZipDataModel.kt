package com.example.zipcodeinfo.data.model2


import com.google.gson.annotations.SerializedName

data class ZipDataModel(
    @SerializedName("country")
    val country: String? = "",
    @SerializedName("country abbreviation")
    val countryAbbreviation: String? = "",
    @SerializedName("places")
    val places: List<ModelPlace?>? = listOf(),
    @SerializedName("post code")
    val postCode: String? = ""
)
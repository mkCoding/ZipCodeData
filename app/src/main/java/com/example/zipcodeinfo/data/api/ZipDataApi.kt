package com.example.zipcodeinfo.data.api

import com.example.zipcodeinfo.data.model2.ZipDataModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ZipDataApi {

    @GET(ApiDetails.ENDPOINT_ZIP_DATA + ApiDetails.ZIP_PATH)
    suspend fun getZipDataBYZip(@Path ("zipCode") zipCode:Int): ZipDataModel
}
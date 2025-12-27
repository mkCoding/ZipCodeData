package com.example.zipcodeinfo.repository

import com.example.zipcodeinfo.data.api.ZipDataApi
import javax.inject.Inject

class ZipDataRepository  @Inject constructor(
    private val api: ZipDataApi
){
    suspend fun getZipDataByZip(zipCode:Int) = api.getZipDataBYZip(zipCode)
}
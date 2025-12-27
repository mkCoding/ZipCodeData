package com.example.zipcodeinfo.di

import com.example.zipcodeinfo.data.api.ApiDetails
import com.example.zipcodeinfo.data.api.ZipDataApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(ApiDetails.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    @Singleton
    fun providesZipDataApi(retrofit: Retrofit): ZipDataApi{
        return retrofit.create(ZipDataApi::class.java)
    }

}
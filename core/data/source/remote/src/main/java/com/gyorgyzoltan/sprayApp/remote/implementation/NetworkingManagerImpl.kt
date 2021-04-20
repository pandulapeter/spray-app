package com.gyorgyzoltan.sprayApp.remote.implementation

import com.gyorgyzoltan.sprayApp.remote.model.NozzleResponse
import com.gyorgyzoltan.sprayApp.remote.model.NozzleTypeResponse
import com.theapache64.retrosheet.RetrosheetInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

internal class NetworkingManagerImpl : NetworkingManager {

    override val service: NetworkingService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(
                    RetrosheetInterceptor.Builder().run {
                        NozzleResponse.addSheet(this)
                        NozzleTypeResponse.addSheet(this)
                    }.build()
                )
                .build()
        )
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(NetworkingService::class.java)

    companion object {
        private const val BASE_URL = "https://docs.google.com/spreadsheets/d/1PdgQmxOK2l34qo4R27J8ETlH2GUVdtn43UL8TONC-Ss/"
    }
}
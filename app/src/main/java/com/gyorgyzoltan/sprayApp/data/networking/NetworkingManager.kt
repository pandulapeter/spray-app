package com.gyorgyzoltan.sprayApp.data.networking

import com.gyorgyzoltan.sprayApp.data.model.remote.NozzleResponse
import com.gyorgyzoltan.sprayApp.data.model.remote.NozzleTypeResponse
import com.gyorgyzoltan.sprayApp.debugMenu.DebugMenu
import com.theapache64.retrosheet.RetrosheetInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NetworkingManager {

    val networkingService: NetworkingService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(
                    RetrosheetInterceptor.Builder()
                        .setLogging(true)
                        .addSheet(
                            NozzleResponse.SHEET_NAME,
                            NozzleResponse.KEY_NAME,
                            NozzleResponse.TYPE,
                            NozzleResponse.COLOR
                        )
                        .addSheet(
                            NozzleTypeResponse.SHEET_NAME,
                            NozzleTypeResponse.KEY_NAME,
                            NozzleTypeResponse.KEY_IMAGE_URL
                        )
                        .build()
                )
                .let(DebugMenu::addInterceptor)
                .build()
        )
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(NetworkingService::class.java)

    companion object {
        const val BASE_URL = "https://docs.google.com/spreadsheets/d/1PdgQmxOK2l34qo4R27J8ETlH2GUVdtn43UL8TONC-Ss/"
    }
}
package com.gyorgyzoltan.sprayApp.data.networking

import com.gyorgyzoltan.sprayApp.data.model.Nozzle
import com.gyorgyzoltan.sprayApp.debugMenu.DebugMenu
import com.theapache64.retrosheet.RetrosheetInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NetworkingManager {

    val nozzleService: NozzleService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(
                    RetrosheetInterceptor.Builder()
                        .setLogging(false)
                        .addSheet(
                            NOZZLES_SHEET_NAME,
                            Nozzle.KEY_CREATED_AT,
                            Nozzle.KEY_NAME,
                            Nozzle.KEY_IMAGE_URL,
                            Nozzle.TYPE
                        )
                        .build()
                )
                .let(DebugMenu::addInterceptor)
                .build()
        )
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(NozzleService::class.java)

    companion object {
        const val NOZZLES_SHEET_NAME = "nozzles"
        const val BASE_URL = "https://docs.google.com/spreadsheets/d/1C-rmJQY1dzboLVql6BHpLnerlRsbXx10Gu1uxIDSEZ8/"
    }
}
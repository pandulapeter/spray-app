package com.gyorgyzoltan.sprayApp.repository.networking

import com.gyorgyzoltan.sprayApp.repository.model.remote.NozzleResponse
import com.theapache64.retrosheet.RetrosheetInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

internal class NetworkingManagerImpl : NetworkingManager {

    override val networkingService: NetworkingService = Retrofit.Builder()
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
                            NozzleResponse.COLOR,
                            NozzleResponse.DEBIT_AT_1_BAR,
                            NozzleResponse.DEBIT_AT_2_BAR,
                            NozzleResponse.DEBIT_AT_3_BAR,
                            NozzleResponse.DEBIT_AT_4_BAR,
                            NozzleResponse.DEBIT_AT_5_BAR,
                            NozzleResponse.DEBIT_AT_6_BAR,
                            NozzleResponse.DEBIT_AT_7_BAR,
                            NozzleResponse.DEBIT_AT_8_BAR
                        )
                        .addSheet(
                            com.gyorgyzoltan.sprayApp.repository.model.remote.NozzleTypeResponse.SHEET_NAME,
                            com.gyorgyzoltan.sprayApp.repository.model.remote.NozzleTypeResponse.KEY_NAME,
                            com.gyorgyzoltan.sprayApp.repository.model.remote.NozzleTypeResponse.KEY_IMAGE_URL
                        )
                        .build()
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
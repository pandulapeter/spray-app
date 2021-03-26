package com.gyorgyzoltan.sprayApp.debugMenu

import android.app.Application
import okhttp3.OkHttpClient

interface DebugMenuContract {

    fun initialize(
        application: Application,
        themeResourceId: Int,
        appName: String,
        versionName: String,
        versionCode: Int,
        applicationId: String,
        buildDate: String,
        baseUrl: String
    ) = Unit

    fun hide() = false

    fun addInterceptor(clientBuilder: OkHttpClient.Builder): OkHttpClient.Builder = clientBuilder
}
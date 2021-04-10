package com.gyorgyzoltan.sprayApp.debugMenu

import android.app.Application

interface DebugMenuContract {

    fun initialize(
        application: Application,
        themeResourceId: Int,
        appName: String,
        versionName: String,
        versionCode: Int,
        applicationId: String,
        buildDate: String
    ) = Unit

    fun hide() = false
}
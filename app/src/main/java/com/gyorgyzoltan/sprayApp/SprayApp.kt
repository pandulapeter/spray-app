package com.gyorgyzoltan.sprayApp

import android.app.Application
import com.gyorgyzoltan.sprayApp.debugMenu.DebugMenu
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class SprayApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SprayApp)
            modules(modules)
        }
        DebugMenu.initialize(
            application = this,
            themeResourceId = R.style.AppTheme,
            appName = getString(R.string.app_name),
            versionName = BuildConfig.VERSION_NAME,
            versionCode = BuildConfig.VERSION_CODE,
            applicationId = BuildConfig.APPLICATION_ID,
            buildDate = BuildConfig.BUILD_DATE
        )
    }
}
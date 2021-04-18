package com.gyorgyzoltan.sprayApp

import android.app.Application
import com.gyorgyzoltan.sprayApp.debugMenu.DebugMenu
import com.gyorgyzoltan.sprayApp.domain.domainModule
import com.gyorgyzoltan.sprayApp.presentation.presentationModule
import com.gyorgyzoltan.sprayApp.repository.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class SprayApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SprayApp)
            modules(repositoryModule + domainModule + presentationModule)
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
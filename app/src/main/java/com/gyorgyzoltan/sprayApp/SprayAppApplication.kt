package com.gyorgyzoltan.sprayApp

import android.app.Application
import com.gyorgyzoltan.sprayApp.debugMenu.DebugMenu
import com.gyorgyzoltan.sprayApp.domain.configuration.IsConfigurationSetUseCase
import com.gyorgyzoltan.sprayApp.domain.domainModule
import com.gyorgyzoltan.sprayApp.domain.nozzle.RefreshNozzlesUseCase
import com.gyorgyzoltan.sprayApp.domain.tutorial.HasSeenTutorialUseCase
import com.gyorgyzoltan.sprayApp.help.featureHelpModule
import com.gyorgyzoltan.sprayApp.localImpl.localSourceModule
import com.gyorgyzoltan.sprayApp.remoteImpl.remoteSourceModule
import com.gyorgyzoltan.sprayApp.repository.repositoryModule
import com.gyorgyzoltan.sprayApp.statistics.featureStatisticsModule
import com.gyorgyzoltan.sprayApp.tutorial.featureTutorialModule
import com.gyorgyzoltan.sprayApp.work.featureWorkModule
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class SprayAppApplication : Application() {

    private val refreshNozzles by inject<RefreshNozzlesUseCase>()
    private val isConfigurationSet by inject<IsConfigurationSetUseCase>()
    private val hasSeenTutorial by inject<HasSeenTutorialUseCase>()

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SprayAppApplication)
            modules(
                localSourceModule + remoteSourceModule + repositoryModule + domainModule +
                        featureTutorialModule + featureWorkModule + featureStatisticsModule + featureHelpModule
            )
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
        if (isConfigurationSet() || !hasSeenTutorial()) {
            GlobalScope.launch { refreshNozzles(true) }
        }
    }
}
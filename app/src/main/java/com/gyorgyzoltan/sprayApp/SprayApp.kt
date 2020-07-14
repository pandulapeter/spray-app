package com.gyorgyzoltan.sprayApp

import android.app.Application
import com.pandulapeter.beagle.Beagle
import com.pandulapeter.beagle.modules.AppInfoButtonModule
import com.pandulapeter.beagle.modules.HeaderModule
import com.pandulapeter.beagle.modules.KeylineOverlaySwitchModule
import com.pandulapeter.beagle.modules.ScreenRecordingButtonModule
import com.pandulapeter.beagle.modules.ScreenshotButtonModule
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
        Beagle.initialize(this)
        Beagle.set(
            HeaderModule(
                title = getString(R.string.app_name),
                subtitle = packageName,
                text = "v${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE}) built on ${BuildConfig.BUILD_DATE}"
            ),
            AppInfoButtonModule(),
            ScreenshotButtonModule(),
            ScreenRecordingButtonModule(),
            KeylineOverlaySwitchModule()
        )
    }
}
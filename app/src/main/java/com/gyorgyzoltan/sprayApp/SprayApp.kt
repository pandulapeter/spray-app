package com.gyorgyzoltan.sprayApp

import android.app.Application
import com.pandulapeter.beagle.Beagle
import com.pandulapeter.beagle.common.configuration.Appearance
import com.pandulapeter.beagle.common.configuration.Insets
import com.pandulapeter.beagle.modules.AppInfoButtonModule
import com.pandulapeter.beagle.modules.DeviceInfoModule
import com.pandulapeter.beagle.modules.DividerModule
import com.pandulapeter.beagle.modules.HeaderModule
import com.pandulapeter.beagle.modules.KeylineOverlaySwitchModule
import com.pandulapeter.beagle.modules.ScreenCaptureToolboxModule
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
        Beagle.initialize(
            application = this,
            appearance = Appearance(
                themeResourceId = R.style.AppTheme,
                applyInsets = { Insets() }
            )
        )
        Beagle.set(
            HeaderModule(
                title = getString(R.string.app_name),
                subtitle = packageName,
                text = "v${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE}) built on ${BuildConfig.BUILD_DATE}"
            ),
            AppInfoButtonModule(),
            KeylineOverlaySwitchModule(
                applyInsets = { Insets() }
            ),
            DividerModule(),
            ScreenCaptureToolboxModule(),
            DeviceInfoModule()
        )
    }
}
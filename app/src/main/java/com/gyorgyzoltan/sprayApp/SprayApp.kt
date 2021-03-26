package com.gyorgyzoltan.sprayApp

import android.app.Application
import com.pandulapeter.beagle.Beagle
import com.pandulapeter.beagle.common.configuration.Appearance
import com.pandulapeter.beagle.common.configuration.Behavior
import com.pandulapeter.beagle.common.configuration.toText
import com.pandulapeter.beagle.logCrash.BeagleCrashLogger
import com.pandulapeter.beagle.modules.AppInfoButtonModule
import com.pandulapeter.beagle.modules.BugReportButtonModule
import com.pandulapeter.beagle.modules.DeveloperOptionsButtonModule
import com.pandulapeter.beagle.modules.DeviceInfoModule
import com.pandulapeter.beagle.modules.DividerModule
import com.pandulapeter.beagle.modules.ForceCrashButtonModule
import com.pandulapeter.beagle.modules.HeaderModule
import com.pandulapeter.beagle.modules.KeylineOverlaySwitchModule
import com.pandulapeter.beagle.modules.LifecycleLogListModule
import com.pandulapeter.beagle.modules.LogListModule
import com.pandulapeter.beagle.modules.PaddingModule
import com.pandulapeter.beagle.modules.ScreenCaptureToolboxModule
import com.pandulapeter.beagle.modules.TextModule
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
        setupDebugMenu()

        Beagle.set(
            HeaderModule(
                title = getString(R.string.app_name),
                subtitle = packageName,
                text = "v${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE}) built on ${BuildConfig.BUILD_DATE}"
            ),
            AppInfoButtonModule(),
            DeveloperOptionsButtonModule(),
            PaddingModule(),
            TextModule("General", type = TextModule.Type.SECTION_HEADER),
            KeylineOverlaySwitchModule(),
            ScreenCaptureToolboxModule(),
            DividerModule(),
            TextModule("Activity", TextModule.Type.SECTION_HEADER),
            LogListModule(),
            LifecycleLogListModule(
                title = "Navigation logs".toText(),
                eventTypes = listOf(LifecycleLogListModule.EventType.FRAGMENT_ON_VIEW_CREATED)
            ),
            TextModule("Other", type = TextModule.Type.SECTION_HEADER),
            DeviceInfoModule(),
            BugReportButtonModule(),
            ForceCrashButtonModule()
        )
    }

    private fun setupDebugMenu() {
        if (BuildConfig.BUILD_TYPE != "release") {
            Beagle.initialize(
                application = this,
                appearance = Appearance(
                    themeResourceId = R.style.AppTheme,
                ),
                behavior = Behavior(
                    bugReportingBehavior = Behavior.BugReportingBehavior(
                        crashLoggers = listOf(BeagleCrashLogger),
                        buildInformation = {
                            listOf(
                                "Version name".toText() to BuildConfig.VERSION_NAME,
                                "Version code".toText() to BuildConfig.VERSION_CODE.toString(),
                                "Application ID".toText() to BuildConfig.APPLICATION_ID,
                                "Build date".toText() to BuildConfig.BUILD_DATE
                            )
                        }
                    )
                )
            )
        }
    }
}
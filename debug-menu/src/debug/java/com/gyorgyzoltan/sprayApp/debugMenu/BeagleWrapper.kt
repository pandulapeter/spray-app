package com.gyorgyzoltan.sprayApp.debugMenu

import android.app.Application
import com.pandulapeter.beagle.Beagle
import com.pandulapeter.beagle.common.configuration.Appearance
import com.pandulapeter.beagle.common.configuration.Behavior
import com.pandulapeter.beagle.common.configuration.toText
import com.pandulapeter.beagle.logCrash.BeagleCrashLogger
import com.pandulapeter.beagle.modules.HeaderModule

internal class BeagleWrapper : DebugMenuContract {

    override fun initialize(
        application: Application,
        themeResourceId: Int,
        appName: String,
        versionName: String,
        versionCode: Int,
        applicationId: String,
        buildDate: String
    ) {
        Beagle.initialize(
            application = application,
            appearance = Appearance(
                themeResourceId = themeResourceId
            ),
            behavior = Behavior(
                bugReportingBehavior = Behavior.BugReportingBehavior(
                    crashLoggers = listOf(BeagleCrashLogger),
                    buildInformation = {
                        listOf(
                            "Version name".toText() to versionName,
                            "Version code".toText() to versionCode.toString(),
                            "Application ID".toText() to applicationId,
                            "Build date".toText() to buildDate
                        )
                    }
                )
            )
        )
        Beagle.set(
            HeaderModule(
                title = appName,
                subtitle = application.packageName,
                text = "v$versionName ($versionCode) built on $buildDate"
            )
        )
    }

    override fun hide() = Beagle.hide()
}
package com.gyorgyzoltan.sprayApp.debugMenu

import android.app.ActivityManager
import android.app.Application
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.pandulapeter.beagle.Beagle
import com.pandulapeter.beagle.common.configuration.Appearance
import com.pandulapeter.beagle.common.configuration.Behavior
import com.pandulapeter.beagle.common.configuration.toText
import com.pandulapeter.beagle.common.contracts.BeagleListItemContract
import com.pandulapeter.beagle.logCrash.BeagleCrashLogger
import com.pandulapeter.beagle.modules.AnimationDurationSwitchModule
import com.pandulapeter.beagle.modules.AppInfoButtonModule
import com.pandulapeter.beagle.modules.BugReportButtonModule
import com.pandulapeter.beagle.modules.DeveloperOptionsButtonModule
import com.pandulapeter.beagle.modules.DeviceInfoModule
import com.pandulapeter.beagle.modules.ForceCrashButtonModule
import com.pandulapeter.beagle.modules.HeaderModule
import com.pandulapeter.beagle.modules.KeylineOverlaySwitchModule
import com.pandulapeter.beagle.modules.LifecycleLogListModule
import com.pandulapeter.beagle.modules.LogListModule
import com.pandulapeter.beagle.modules.NetworkLogListModule
import com.pandulapeter.beagle.modules.PaddingModule
import com.pandulapeter.beagle.modules.ScreenCaptureToolboxModule
import com.pandulapeter.beagle.modules.SingleSelectionListModule
import com.pandulapeter.beagle.modules.TextModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal class BeagleWrapper : DebugMenuContract, CoroutineScope {

    override val coroutineContext = SupervisorJob() + Dispatchers.Default

    private val uiModes = listOf(
        UiMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM, "System setting"),
        UiMode(AppCompatDelegate.MODE_NIGHT_NO, "Light"),
        UiMode(AppCompatDelegate.MODE_NIGHT_YES, "Dark")
    )

    private val uiModeSelectionModule = SingleSelectionListModule(
        title = "UI mode",
        id = "uiMode",
        items = uiModes,
        isValuePersisted = true,
        initiallySelectedItemId = uiModes.first().id,
        onSelectionChanged = ::applyUiMode
    )

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
        val buildInformation = "v$versionName ($versionCode)"
        Beagle.set(
            // Header
            HeaderModule(
                title = appName,
                subtitle = "Built on $buildDate"
            ),
            TextModule(
                text = "Copy build information".toText(),
                onItemSelected = { copyBuildInformationToClipboard(buildInformation) }
            ),
            PaddingModule(),
            // General
            TextModule(
                text = "General".toText(),
                type = TextModule.Type.SECTION_HEADER,
                icon = R.drawable.ic_debug_menu_general
            ),
            KeylineOverlaySwitchModule(
                applyInsets = { it.copy(top = 0) }
            ),
            AnimationDurationSwitchModule(),
            ScreenCaptureToolboxModule(),
            BugReportButtonModule(),
            PaddingModule(size = PaddingModule.Size.LARGE),
            // Configuration
            TextModule(
                text = "Configuration".toText(),
                type = TextModule.Type.SECTION_HEADER,
                icon = R.drawable.ic_debug_menu_configuration
            ),
            uiModeSelectionModule,
            TextModule(
                text = "Clear local app data".toText(),
                onItemSelected = ::clearAppData
            ),
            PaddingModule(size = PaddingModule.Size.LARGE),
            // System shortcuts
            TextModule(
                text = "System shortcuts".toText(),
                type = TextModule.Type.SECTION_HEADER,
                icon = R.drawable.ic_debug_menu_system_shortcuts
            ),
            AppInfoButtonModule(
                type = TextModule.Type.NORMAL
            ),
            DeveloperOptionsButtonModule(
                type = TextModule.Type.NORMAL
            ),
            TextModule(
                text = "Language settings".toText(),
                onItemSelected = ::openLanguageSettings
            ),
            PaddingModule(size = PaddingModule.Size.LARGE),
            // Logs
            TextModule(
                text = "Logs".toText(),
                type = TextModule.Type.SECTION_HEADER,
                icon = R.drawable.ic_debug_menu_logs
            ),
            LogListModule(
                title = "General".toText()
            ),
            NetworkLogListModule(
                title = "Networking".toText()
            ),
            LifecycleLogListModule(
                title = "Navigation".toText(),
                eventTypes = listOf(LifecycleLogListModule.EventType.FRAGMENT_ON_VIEW_CREATED)
            ),
            PaddingModule(size = PaddingModule.Size.LARGE),
            // Other
            TextModule(
                text = "Other".toText(),
                type = TextModule.Type.SECTION_HEADER,
                icon = R.drawable.ic_debug_menu_other
            ),
            DeviceInfoModule(),
            ForceCrashButtonModule()
        )
    }

    override fun hide() = Beagle.hide()

    private fun copyBuildInformationToClipboard(buildInformation: String) {
        Beagle.currentActivity?.run {
            (getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager)
                .setPrimaryClip(ClipData.newPlainText("Build information", buildInformation))
            Toast.makeText(this, "Copied to clipboard", Toast.LENGTH_SHORT).show()
        }
    }

    private fun applyUiMode(uiMode: UiMode?) {
        if (uiMode != null) {
            launch(Dispatchers.Main) { uiMode.uiModeConstant.let(AppCompatDelegate::setDefaultNightMode) }
        }
    }

    private fun clearAppData() {
        Beagle.currentActivity?.run {
            Toast.makeText(this, "The app will close...", Toast.LENGTH_SHORT).show()
            launch {
                delay(PROCESS_RESTART_DELAY)
                (getSystemService(Context.ACTIVITY_SERVICE) as? ActivityManager)?.clearApplicationUserData()
            }
        }
    }

    private fun openLanguageSettings() {
        Beagle.currentActivity?.startActivity(
            Intent(Settings.ACTION_LOCALE_SETTINGS).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
        )
    }

    private data class UiMode(
        val uiModeConstant: Int,
        val name: String
    ) : BeagleListItemContract {

        override val id = name
        override val title = name.toText()
    }

    companion object {
        private const val PROCESS_RESTART_DELAY = 1000L
    }
}
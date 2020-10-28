package com.gyorgyzoltan.sprayApp.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.gyorgyzoltan.sprayApp.R
import com.gyorgyzoltan.sprayApp.data.PreferenceManager
import com.gyorgyzoltan.sprayApp.feature.main.MainFragment
import com.gyorgyzoltan.sprayApp.feature.shared.BaseFragment
import com.gyorgyzoltan.sprayApp.feature.tutorial.TutorialFragment
import com.gyorgyzoltan.sprayApp.utils.TransitionType
import com.gyorgyzoltan.sprayApp.utils.handleReplace
import com.pandulapeter.beagle.Beagle
import org.koin.android.ext.android.inject

class SprayAppActivity : AppCompatActivity() {

    private val currentFragment get() = supportFragmentManager.findFragmentById(android.R.id.content) as? BaseFragment<*>?
    private val preferenceManager by inject<PreferenceManager>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        if (savedInstanceState == null) {
            if (preferenceManager.hasSeenTutorial) {
                navigateToMain()
            } else {
                navigateToTutorial(true)
            }
        }
    }

    fun navigateToTutorial(isFirstTutorial: Boolean) = supportFragmentManager.handleReplace(
        containerId = android.R.id.content,
        addToBackStack = !isFirstTutorial,
        transitionType = if (isFirstTutorial) null else TransitionType.MODAL
    ) { TutorialFragment.newInstance(isFirstTutorial) }

    private fun navigateToMain() = supportFragmentManager.handleReplace(
        containerId = android.R.id.content,
        transitionType = null,
        newInstance = MainFragment.Companion::newInstance
    )

    override fun onBackPressed() {
        if (!Beagle.hide() && currentFragment?.onBackPressed() != true) {
            super.onBackPressed()
        }
    }
}
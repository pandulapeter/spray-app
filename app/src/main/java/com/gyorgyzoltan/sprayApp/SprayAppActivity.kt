package com.gyorgyzoltan.sprayApp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.gyorgyzoltan.sprayApp.databinding.ActivitySprayAppBinding
import com.gyorgyzoltan.sprayApp.debugMenu.DebugMenu
import com.gyorgyzoltan.sprayApp.domain.tutorial.HasSeenTutorialUseCase
import com.gyorgyzoltan.sprayApp.help.overview.HelpContainerFragment
import com.gyorgyzoltan.sprayApp.main.overview.MainFragment
import com.gyorgyzoltan.sprayApp.main.shared.BaseFragment
import com.gyorgyzoltan.sprayApp.main.shared.Navigator
import com.gyorgyzoltan.sprayApp.main.shared.utilities.TransitionType
import com.gyorgyzoltan.sprayApp.main.shared.utilities.handleReplace
import com.gyorgyzoltan.sprayApp.statistics.overview.StatisticsContainerFragment
import com.gyorgyzoltan.sprayApp.tutorial.overview.TutorialFragment
import com.gyorgyzoltan.sprayApp.work.overview.WorkContainerFragment
import org.koin.android.ext.android.inject

class SprayAppActivity : AppCompatActivity(), Navigator {

    private val currentFragment get() = supportFragmentManager.findFragmentById(R.id.fragment_container) as? BaseFragment<*>?
    private val hasSeenTutorial by inject<HasSeenTutorialUseCase>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivitySprayAppBinding>(this, R.layout.activity_spray_app)
        if (savedInstanceState == null) {
            if (hasSeenTutorial()) {
                navigateToMain()
            } else {
                navigateToTutorial(true)
            }
        }
    }

    override fun navigateToMain() = supportFragmentManager.handleReplace(
        transitionType = null,
        newInstance = MainFragment.Companion::newInstance
    )

    override fun navigateToTutorial(isFirstTutorial: Boolean) = supportFragmentManager.handleReplace(
        addToBackStack = !isFirstTutorial,
        transitionType = if (isFirstTutorial) null else TransitionType.MODAL
    ) { TutorialFragment.newInstance(isFirstTutorial) }

    override fun createWorkFragmentInstance() = WorkContainerFragment.newInstance()

    override fun createStatisticsFragmentInstance() = StatisticsContainerFragment.newInstance()

    override fun createHelpFragmentInstance() = HelpContainerFragment.newInstance()

    override fun onBackPressed() {
        if (!DebugMenu.hide() && currentFragment?.onBackPressed() != true) {
            if (supportFragmentManager.backStackEntryCount >= 1) {
                super.onBackPressed()
            } else {
                closeAfterConfirmation()
            }
        }
    }

    private fun closeAfterConfirmation() = MaterialAlertDialogBuilder(this)
        .setTitle(R.string.close_confirmation_title)
        .setMessage(R.string.close_confirmation_message)
        .setPositiveButton(R.string.close_confirmation_positive) { _, _ -> supportFinishAfterTransition() }
        .setNegativeButton(R.string.close_confirmation_negative, null)
        .show()
}
package com.gyorgyzoltan.sprayApp.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.gyorgyzoltan.sprayApp.R
import com.gyorgyzoltan.sprayApp.data.PreferenceManager
import com.gyorgyzoltan.sprayApp.databinding.ActivitySprayAppBinding
import com.gyorgyzoltan.sprayApp.feature.main.MainFragment
import com.gyorgyzoltan.sprayApp.feature.shared.BaseFragment
import com.gyorgyzoltan.sprayApp.feature.tutorial.TutorialFragment
import com.gyorgyzoltan.sprayApp.utils.handleReplace
import com.pandulapeter.beagle.Beagle
import org.koin.android.ext.android.inject

class SprayAppActivity : AppCompatActivity() {

    private val currentFragment get() = supportFragmentManager.findFragmentById(R.id.fragment_container) as? BaseFragment<*>?
    private val preferenceManager by inject<PreferenceManager>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivitySprayAppBinding>(this, R.layout.activity_spray_app)
        if (savedInstanceState == null) {
            if (preferenceManager.hasSeenTutorial) {
                supportFragmentManager.handleReplace(transitionType = null, newInstance = MainFragment.Companion::newInstance)
            } else {
                supportFragmentManager.handleReplace(transitionType = null) { TutorialFragment.newInstance(true) }
            }
        }
    }

    override fun onBackPressed() {
        if (!Beagle.hide() && currentFragment?.onBackPressed() != true) {
            super.onBackPressed()
        }
    }
}
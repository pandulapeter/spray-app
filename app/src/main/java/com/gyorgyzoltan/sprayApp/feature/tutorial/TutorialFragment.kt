package com.gyorgyzoltan.sprayApp.feature.tutorial

import android.os.Bundle
import android.view.View
import com.gyorgyzoltan.sprayApp.R
import com.gyorgyzoltan.sprayApp.data.PreferenceManager
import com.gyorgyzoltan.sprayApp.databinding.FragmentTutorialBinding
import com.gyorgyzoltan.sprayApp.feature.main.MainFragment
import com.gyorgyzoltan.sprayApp.feature.shared.BaseFragment
import com.gyorgyzoltan.sprayApp.utils.BundleArgumentDelegate
import com.gyorgyzoltan.sprayApp.utils.handleReplace
import com.gyorgyzoltan.sprayApp.utils.visible
import com.gyorgyzoltan.sprayApp.utils.withArguments
import org.koin.android.ext.android.inject

class TutorialFragment : BaseFragment<FragmentTutorialBinding>(R.layout.fragment_tutorial) {

    private val preferenceManager by inject<PreferenceManager>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val isFirstTutorial = arguments?.isFirstTutorial == true
        binding.skipButton.setOnClickListener {
            if (isFirstTutorial) {
                preferenceManager.hasSeenTutorial = true
                activityFragmentManager?.handleReplace(newInstance = MainFragment.Companion::newInstance)
            } else {
                activityFragmentManager?.popBackStack()
            }
        }
        binding.closeButton.run {
            if (isFirstTutorial) {
                visible = false
            } else {
                setOnClickListener { activityFragmentManager?.popBackStack() }
            }
        }
    }

    companion object {
        private var Bundle.isFirstTutorial by BundleArgumentDelegate.Boolean("isFirstTutorial")

        fun newInstance(isFirstTutorial: Boolean) = TutorialFragment().withArguments {
            it.isFirstTutorial = isFirstTutorial
        }
    }
}
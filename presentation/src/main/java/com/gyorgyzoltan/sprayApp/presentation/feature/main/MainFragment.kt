package com.gyorgyzoltan.sprayApp.presentation.feature.main

import android.os.Bundle
import android.view.View
import com.gyorgyzoltan.sprayApp.presentation.R
import com.gyorgyzoltan.sprayApp.presentation.databinding.FragmentMainBinding
import com.gyorgyzoltan.sprayApp.presentation.feature.main.help.HelpContainerFragment
import com.gyorgyzoltan.sprayApp.presentation.feature.main.statistics.StatisticsContainerFragment
import com.gyorgyzoltan.sprayApp.presentation.feature.main.work.WorkContainerFragment
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.BaseFragment
import com.gyorgyzoltan.sprayApp.presentation.utils.consume
import com.gyorgyzoltan.sprayApp.presentation.utils.handleReplace

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            consume {
                when (item.itemId) {
                    R.id.work -> childFragmentManager.handleReplace(addToBackStack = true, newInstance = WorkContainerFragment.Companion::newInstance)
                    R.id.statistics -> childFragmentManager.handleReplace(addToBackStack = true, newInstance = StatisticsContainerFragment.Companion::newInstance)
                    R.id.help -> childFragmentManager.handleReplace(addToBackStack = true, newInstance = HelpContainerFragment.Companion::newInstance)
                    else -> throw  IllegalArgumentException("Unsupported bottom navigation item.")
                }
            }
        }
        if (savedInstanceState == null && currentFragment == null) {
            binding.bottomNavigationView.selectedItemId = R.id.work
        }
        binding.bottomNavigationView.setOnNavigationItemReselectedListener { onBackPressed() }
    }

    override fun onBackPressed() = if (currentFragment?.childFragmentManager?.backStackEntryCount ?: 0 <= 1) {
        if (binding.bottomNavigationView.selectedItemId == R.id.work) {
            false
        } else consume {
            binding.bottomNavigationView.selectedItemId = R.id.work
        }
    } else {
        super.onBackPressed()
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
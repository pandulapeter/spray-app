package com.gyorgyzoltan.sprayApp.feature.main

import android.os.Bundle
import android.view.View
import com.gyorgyzoltan.sprayApp.R
import com.gyorgyzoltan.sprayApp.databinding.FragmentMainBinding
import com.gyorgyzoltan.sprayApp.feature.main.help.HelpContainerFragment
import com.gyorgyzoltan.sprayApp.feature.main.statistics.StatisticsContainerFragment
import com.gyorgyzoltan.sprayApp.feature.main.work.WorkContainerFragment
import com.gyorgyzoltan.sprayApp.feature.shared.BaseFragment
import com.gyorgyzoltan.sprayApp.utils.consume
import com.gyorgyzoltan.sprayApp.utils.handleReplace

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

    override fun onBackPressed() = if (currentFragment?.childFragmentManager?.backStackEntryCount ?: 0 <= 1) false else super.onBackPressed()

    companion object {
        fun newInstance() = MainFragment()
    }
}
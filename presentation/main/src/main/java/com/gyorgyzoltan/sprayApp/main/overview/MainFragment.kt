package com.gyorgyzoltan.sprayApp.main.overview

import android.os.Bundle
import android.view.View
import com.gyorgyzoltan.sprayApp.main.R
import com.gyorgyzoltan.sprayApp.main.databinding.FragmentMainBinding
import com.gyorgyzoltan.sprayApp.main.shared.BaseFragment
import com.gyorgyzoltan.sprayApp.main.shared.utilities.consume
import com.gyorgyzoltan.sprayApp.main.shared.utilities.handleReplace

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            consume {
                navigator?.run {
                    when (item.itemId) {
                        R.id.work -> childFragmentManager.handleReplace(
                            addToBackStack = true,
                            tag = "work",
                            newInstance = ::createWorkFragmentInstance
                        )
                        R.id.statistics -> childFragmentManager.handleReplace(
                            addToBackStack = true,
                            tag = "statistics",
                            newInstance = ::createStatisticsFragmentInstance
                        )
                        R.id.help -> childFragmentManager.handleReplace(
                            addToBackStack = true,
                            tag = "help",
                            newInstance = ::createHelpFragmentInstance
                        )
                        else -> throw  IllegalArgumentException("Unsupported bottom navigation item.")
                    }
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
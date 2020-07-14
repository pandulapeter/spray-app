package com.gyorgyzoltan.sprayApp.feature.main

import android.os.Bundle
import android.view.View
import com.gyorgyzoltan.sprayApp.R
import com.gyorgyzoltan.sprayApp.databinding.FragmentMainBinding
import com.gyorgyzoltan.sprayApp.feature.shared.BaseFragment
import com.gyorgyzoltan.sprayApp.utils.consume

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            consume {
                when (item.itemId) {
                    R.id.work -> Unit //TODO childFragmentManager.handleReplace(addToBackStack = true, newInstance = SetupContainerFragment.Companion::newInstance)
                    R.id.statistics -> Unit //TODO childFragmentManager.handleReplace(addToBackStack = true, newInstance = ExamplesContainerFragment.Companion::newInstance)
                    R.id.help -> Unit //TODO childFragmentManager.handleReplace(addToBackStack = true, newInstance = PlaygroundContainerFragment.Companion::newInstance)
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
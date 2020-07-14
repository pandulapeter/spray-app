package com.gyorgyzoltan.sprayApp.feature.main

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import com.gyorgyzoltan.sprayApp.R
import com.gyorgyzoltan.sprayApp.databinding.FragmentContainerBinding
import com.gyorgyzoltan.sprayApp.feature.shared.BaseFragment
import com.gyorgyzoltan.sprayApp.feature.shared.ListFragment
import com.gyorgyzoltan.sprayApp.utils.handleReplace

abstract class ContainerFragment(private val newInstance: () -> ListFragment<*, *>) : BaseFragment<FragmentContainerBinding>(R.layout.fragment_container) {

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (savedInstanceState == null && currentFragment == null) {
            childFragmentManager.handleReplace(addToBackStack = true, newInstance = newInstance)
        }
    }
}
package com.gyorgyzoltan.sprayApp.presentation.feature.main

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import com.gyorgyzoltan.sprayApp.presentation.R
import com.gyorgyzoltan.sprayApp.presentation.databinding.FragmentContainerBinding
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.BaseFragment
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.ListFragment
import com.gyorgyzoltan.sprayApp.presentation.utils.handleReplace

internal abstract class ContainerFragment(private val newInstance: () -> ListFragment<*, *>) : BaseFragment<FragmentContainerBinding>(R.layout.fragment_container) {

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (savedInstanceState == null && currentFragment == null) {
            childFragmentManager.handleReplace(addToBackStack = true, newInstance = newInstance)
        }
    }
}
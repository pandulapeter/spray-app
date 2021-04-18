package com.gyorgyzoltan.sprayApp.main.shared

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import com.gyorgyzoltan.sprayApp.main.R
import com.gyorgyzoltan.sprayApp.main.databinding.FragmentContainerBinding
import com.gyorgyzoltan.sprayApp.main.shared.list.ListFragment
import com.gyorgyzoltan.sprayApp.main.shared.utilities.handleReplace

abstract class ContainerFragment(private val newInstance: () -> ListFragment<*, *>) : BaseFragment<FragmentContainerBinding>(R.layout.fragment_container) {

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (savedInstanceState == null && currentFragment == null) {
            childFragmentManager.handleReplace(addToBackStack = true, newInstance = newInstance)
        }
    }
}
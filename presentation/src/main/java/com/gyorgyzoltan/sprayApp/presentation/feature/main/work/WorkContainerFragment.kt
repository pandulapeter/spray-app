package com.gyorgyzoltan.sprayApp.presentation.feature.main.work

import com.gyorgyzoltan.sprayApp.presentation.feature.main.ContainerFragment
import com.gyorgyzoltan.sprayApp.presentation.feature.main.work.configuration.ConfigurationFragment
import com.gyorgyzoltan.sprayApp.presentation.feature.main.work.configuration.nozzlePicker.NozzlePickerFragment
import com.gyorgyzoltan.sprayApp.repository.preferences.PreferenceManager
import com.gyorgyzoltan.sprayApp.presentation.utils.TransitionType
import com.gyorgyzoltan.sprayApp.presentation.utils.handleReplace
import org.koin.core.context.KoinContextHandler

internal class WorkContainerFragment : ContainerFragment({
    if (KoinContextHandler.get().get<PreferenceManager>().isConfigurationSet) {
        WorkFragment.newInstance()
    } else {
        ConfigurationFragment.newInstance()
    }
}) {

    fun navigateBack() = childFragmentManager.popBackStack()

    fun navigateToWork() = childFragmentManager.run {
        if (backStackEntryCount > 1) {
            popBackStack()
        } else {
            handleReplace(newInstance = WorkFragment.Companion::newInstance)
        }
    }

    fun navigateToConfiguration() = childFragmentManager.handleReplace(
        addToBackStack = true,
        transitionType = TransitionType.MODAL,
        newInstance = ConfigurationFragment.Companion::newInstance
    )

    fun navigateToNozzlePicker() = childFragmentManager.handleReplace(
        addToBackStack = true,
        transitionType = TransitionType.MODAL,
        newInstance = NozzlePickerFragment.Companion::newInstance
    )

    companion object {
        fun newInstance() = WorkContainerFragment()
    }
}
package com.gyorgyzoltan.sprayApp.work.overview

import com.gyorgyzoltan.sprayApp.domain.configuration.IsConfigurationSetUseCase
import com.gyorgyzoltan.sprayApp.main.shared.ContainerFragment
import com.gyorgyzoltan.sprayApp.work.configuration.ConfigurationFragment
import com.gyorgyzoltan.sprayApp.work.nozzlePicker.NozzlePickerFragment
import com.gyorgyzoltan.sprayApp.main.shared.utilities.TransitionType
import com.gyorgyzoltan.sprayApp.main.shared.utilities.handleReplace
import org.koin.core.context.KoinContextHandler

class WorkContainerFragment : ContainerFragment({
    if (KoinContextHandler.get().get<IsConfigurationSetUseCase>().invoke()) {
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
package com.gyorgyzoltan.sprayApp.work.overview

import com.gyorgyzoltan.sprayApp.domain.configuration.IsConfigurationSetUseCase
import com.gyorgyzoltan.sprayApp.main.shared.ContainerFragment
import com.gyorgyzoltan.sprayApp.main.shared.utilities.TransitionType
import com.gyorgyzoltan.sprayApp.main.shared.utilities.handleReplace
import com.gyorgyzoltan.sprayApp.work.configuration.ConfigurationFragment
import com.gyorgyzoltan.sprayApp.work.nozzleCountPicker.NozzleCountPickerFragment
import com.gyorgyzoltan.sprayApp.work.nozzleDistancePicker.NozzleDistancePickerFragment
import com.gyorgyzoltan.sprayApp.work.nozzlePicker.NozzlePickerFragment
import com.gyorgyzoltan.sprayApp.work.screwCountPicker.ScrewCountPickerFragment
import com.gyorgyzoltan.sprayApp.work.wheelRadiusPicker.WheelRadiusPickerFragment
import org.koin.core.context.GlobalContext

class WorkContainerFragment : ContainerFragment({
    if (GlobalContext.get().get<IsConfigurationSetUseCase>().invoke()) {
        WorkFragment.newInstance()
    } else {
        ConfigurationFragment.newInstance()
    }
}) {

    internal fun navigateBack() = childFragmentManager.popBackStack()

    internal fun navigateToWork() = childFragmentManager.run {
        if (backStackEntryCount > 1) {
            popBackStack()
        } else {
            handleReplace(newInstance = WorkFragment.Companion::newInstance)
        }
    }

    internal fun navigateToConfiguration() = childFragmentManager.handleReplace(
        addToBackStack = true,
        transitionType = TransitionType.MODAL,
        newInstance = ConfigurationFragment.Companion::newInstance
    )

    internal fun navigateToNozzlePicker() = childFragmentManager.handleReplace(
        addToBackStack = true,
        transitionType = TransitionType.MODAL,
        newInstance = NozzlePickerFragment.Companion::newInstance
    )

    internal fun navigateToWheelRadiusPicker(currentWheelRadius: Float?) = childFragmentManager.handleReplace(
        addToBackStack = true,
        transitionType = TransitionType.MODAL,
        newInstance = { WheelRadiusPickerFragment.newInstance(currentWheelRadius) }
    )

    internal fun navigateToScrewCountPicker(currentScrewCount: Int?) = childFragmentManager.handleReplace(
        addToBackStack = true,
        transitionType = TransitionType.MODAL,
        newInstance = { ScrewCountPickerFragment.newInstance(currentScrewCount) }
    )

    internal fun navigateToNozzleCountPicker(currentNozzleCount: Int?) = childFragmentManager.handleReplace(
        addToBackStack = true,
        transitionType = TransitionType.MODAL,
        newInstance = { NozzleCountPickerFragment.newInstance(currentNozzleCount) }
    )

    internal fun navigateToNozzleDistancePicker(currentNozzleDistance: Float?) = childFragmentManager.handleReplace(
        addToBackStack = true,
        transitionType = TransitionType.MODAL,
        newInstance = { NozzleDistancePickerFragment.newInstance(currentNozzleDistance) }
    )

    companion object {
        fun newInstance() = WorkContainerFragment()
    }
}
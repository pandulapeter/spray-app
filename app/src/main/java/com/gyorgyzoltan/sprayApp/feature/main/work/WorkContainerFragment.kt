package com.gyorgyzoltan.sprayApp.feature.main.work

import com.gyorgyzoltan.sprayApp.data.PreferenceManager
import com.gyorgyzoltan.sprayApp.feature.main.ContainerFragment
import com.gyorgyzoltan.sprayApp.feature.main.work.configuration.ConfigurationFragment
import com.gyorgyzoltan.sprayApp.utils.handleReplace
import org.koin.core.context.KoinContextHandler


class WorkContainerFragment : ContainerFragment({
    if (KoinContextHandler.get().get<PreferenceManager>().isConfigurationSet) {
        WorkFragment.newInstance()
    } else {
        ConfigurationFragment.newInstance()
    }
}) {

    fun navigateToWork() = childFragmentManager.run {
        if (backStackEntryCount > 1) {
            popBackStack()
        } else {
            handleReplace(newInstance = WorkFragment.Companion::newInstance)
        }
    }

    companion object {
        fun newInstance() = WorkContainerFragment()
    }
}
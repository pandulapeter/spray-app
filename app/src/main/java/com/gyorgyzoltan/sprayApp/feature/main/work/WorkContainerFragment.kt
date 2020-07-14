package com.gyorgyzoltan.sprayApp.feature.main.work

import com.gyorgyzoltan.sprayApp.feature.main.ContainerFragment

class WorkContainerFragment : ContainerFragment(WorkFragment.Companion::newInstance) {

    companion object {
        fun newInstance() = WorkContainerFragment()
    }
}
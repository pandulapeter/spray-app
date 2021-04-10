package com.gyorgyzoltan.sprayApp.presentation.feature.main.help

import com.gyorgyzoltan.sprayApp.presentation.feature.main.ContainerFragment

internal class HelpContainerFragment : ContainerFragment(HelpFragment.Companion::newInstance) {

    companion object {
        fun newInstance() = HelpContainerFragment()
    }
}
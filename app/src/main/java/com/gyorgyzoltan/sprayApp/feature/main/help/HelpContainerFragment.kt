package com.gyorgyzoltan.sprayApp.feature.main.help

import com.gyorgyzoltan.sprayApp.feature.main.ContainerFragment

class HelpContainerFragment : ContainerFragment(HelpFragment.Companion::newInstance) {

    companion object {
        fun newInstance() = HelpContainerFragment()
    }
}
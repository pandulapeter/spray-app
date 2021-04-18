package com.gyorgyzoltan.sprayApp.help.overview

import com.gyorgyzoltan.sprayApp.main.shared.ContainerFragment

class HelpContainerFragment : ContainerFragment(HelpFragment.Companion::newInstance) {

    companion object {
        fun newInstance() = HelpContainerFragment()
    }
}
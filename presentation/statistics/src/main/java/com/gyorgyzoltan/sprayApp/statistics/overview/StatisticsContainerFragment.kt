package com.gyorgyzoltan.sprayApp.statistics.overview

import com.gyorgyzoltan.sprayApp.main.shared.ContainerFragment

class StatisticsContainerFragment : ContainerFragment(StatisticsFragment.Companion::newInstance) {

    companion object {
        fun newInstance() = StatisticsContainerFragment()
    }
}
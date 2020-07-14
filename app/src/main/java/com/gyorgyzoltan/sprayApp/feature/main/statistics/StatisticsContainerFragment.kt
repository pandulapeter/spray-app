package com.gyorgyzoltan.sprayApp.feature.main.statistics

import com.gyorgyzoltan.sprayApp.feature.main.ContainerFragment

class StatisticsContainerFragment : ContainerFragment(StatisticsFragment.Companion::newInstance) {

    companion object {
        fun newInstance() = StatisticsContainerFragment()
    }
}
package com.gyorgyzoltan.sprayApp.presentation.feature.main.statistics

import com.gyorgyzoltan.sprayApp.presentation.feature.main.ContainerFragment

internal class StatisticsContainerFragment : ContainerFragment(StatisticsFragment.Companion::newInstance) {

    companion object {
        fun newInstance() = StatisticsContainerFragment()
    }
}
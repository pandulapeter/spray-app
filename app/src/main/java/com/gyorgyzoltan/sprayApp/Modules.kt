package com.gyorgyzoltan.sprayApp

import com.gyorgyzoltan.sprayApp.feature.main.help.HelpViewModel
import com.gyorgyzoltan.sprayApp.feature.main.statistics.StatisticsViewModel
import com.gyorgyzoltan.sprayApp.feature.main.work.WorkViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val dataModule = module {
}

private val featureModule = module {
    viewModel { WorkViewModel() }
    viewModel { StatisticsViewModel() }
    viewModel { HelpViewModel() }
}

val modules = dataModule + featureModule
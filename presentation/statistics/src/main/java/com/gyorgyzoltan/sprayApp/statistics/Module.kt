package com.gyorgyzoltan.sprayApp.statistics

import com.gyorgyzoltan.sprayApp.statistics.overview.StatisticsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureStatisticsModule = module {
    viewModel { StatisticsViewModel() }
}
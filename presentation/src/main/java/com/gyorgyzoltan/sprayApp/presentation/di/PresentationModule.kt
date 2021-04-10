package com.gyorgyzoltan.sprayApp.presentation.di

import com.gyorgyzoltan.sprayApp.presentation.feature.main.help.HelpViewModel
import com.gyorgyzoltan.sprayApp.presentation.feature.main.help.licences.LicencesViewModel
import com.gyorgyzoltan.sprayApp.presentation.feature.main.statistics.StatisticsViewModel
import com.gyorgyzoltan.sprayApp.presentation.feature.main.work.WorkViewModel
import com.gyorgyzoltan.sprayApp.presentation.feature.main.work.configuration.ConfigurationViewModel
import com.gyorgyzoltan.sprayApp.presentation.feature.main.work.configuration.nozzlePicker.NozzlePickerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { ConfigurationViewModel(get(), get()) }
    viewModel { NozzlePickerViewModel(get(), get()) }
    viewModel { WorkViewModel() }
    viewModel { StatisticsViewModel() }
    viewModel { HelpViewModel() }
    viewModel { LicencesViewModel() }
}
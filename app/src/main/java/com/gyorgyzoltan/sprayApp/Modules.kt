package com.gyorgyzoltan.sprayApp

import com.gyorgyzoltan.sprayApp.data.PreferenceManager
import com.gyorgyzoltan.sprayApp.feature.main.help.HelpViewModel
import com.gyorgyzoltan.sprayApp.feature.main.help.licences.LicencesViewModel
import com.gyorgyzoltan.sprayApp.feature.main.statistics.StatisticsViewModel
import com.gyorgyzoltan.sprayApp.feature.main.work.WorkViewModel
import com.gyorgyzoltan.sprayApp.feature.main.work.configuration.ConfigurationViewModel
import com.gyorgyzoltan.sprayApp.feature.main.work.configuration.nozzlePicker.NozzlePickerViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val dataModule = module {
    single { PreferenceManager(androidContext()) }
}

private val featureModule = module {
    viewModel { ConfigurationViewModel(get()) }
    viewModel { NozzlePickerViewModel(get()) }
    viewModel { WorkViewModel() }
    viewModel { StatisticsViewModel() }
    viewModel { HelpViewModel() }
    viewModel { LicencesViewModel() }
}

val modules = dataModule + featureModule
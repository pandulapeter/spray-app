package com.gyorgyzoltan.sprayApp.di

import com.gyorgyzoltan.sprayApp.domain.di.domainModule
import com.gyorgyzoltan.sprayApp.feature.main.help.HelpViewModel
import com.gyorgyzoltan.sprayApp.feature.main.help.licences.LicencesViewModel
import com.gyorgyzoltan.sprayApp.feature.main.statistics.StatisticsViewModel
import com.gyorgyzoltan.sprayApp.feature.main.work.WorkViewModel
import com.gyorgyzoltan.sprayApp.feature.main.work.configuration.ConfigurationViewModel
import com.gyorgyzoltan.sprayApp.feature.main.work.configuration.nozzlePicker.NozzlePickerViewModel
import com.gyorgyzoltan.sprayApp.repository.di.repositoryModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val featureModule = module {
    viewModel { ConfigurationViewModel(get(), get()) }
    viewModel { NozzlePickerViewModel(get(), get()) }
    viewModel { WorkViewModel() }
    viewModel { StatisticsViewModel() }
    viewModel { HelpViewModel() }
    viewModel { LicencesViewModel() }
}

val appModules = repositoryModule + domainModule + featureModule
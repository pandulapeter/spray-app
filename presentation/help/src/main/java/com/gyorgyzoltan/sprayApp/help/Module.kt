package com.gyorgyzoltan.sprayApp.help

import com.gyorgyzoltan.sprayApp.help.licences.LicencesViewModel
import com.gyorgyzoltan.sprayApp.help.overview.HelpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureHelpModule = module {
    viewModel { HelpViewModel() }
    viewModel { LicencesViewModel() }
}
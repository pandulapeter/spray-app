package com.gyorgyzoltan.sprayApp.work

import com.gyorgyzoltan.sprayApp.work.overview.WorkViewModel
import com.gyorgyzoltan.sprayApp.work.configuration.ConfigurationViewModel
import com.gyorgyzoltan.sprayApp.work.nozzlePicker.NozzlePickerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureWorkModule = module {
    viewModel { ConfigurationViewModel() }
    viewModel { NozzlePickerViewModel(get(), get()) }
    viewModel { WorkViewModel() }
}
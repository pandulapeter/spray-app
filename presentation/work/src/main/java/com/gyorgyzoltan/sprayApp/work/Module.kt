package com.gyorgyzoltan.sprayApp.work

import com.gyorgyzoltan.sprayApp.work.configuration.ConfigurationViewModel
import com.gyorgyzoltan.sprayApp.work.nozzleCountPicker.NozzleCountPickerViewModel
import com.gyorgyzoltan.sprayApp.work.nozzleDistancePicker.NozzleDistancePickerViewModel
import com.gyorgyzoltan.sprayApp.work.nozzlePicker.NozzlePickerViewModel
import com.gyorgyzoltan.sprayApp.work.overview.WorkViewModel
import com.gyorgyzoltan.sprayApp.work.screwCountPicker.ScrewCountPickerViewModel
import com.gyorgyzoltan.sprayApp.work.wheelRadiusPicker.WheelRadiusPickerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureWorkModule = module {
    viewModel { ConfigurationViewModel(get(), get()) }
    viewModel { (initialNozzleCount: Int) -> NozzleCountPickerViewModel(initialNozzleCount, get()) }
    viewModel { (initialNozzleDistance: Float) -> NozzleDistancePickerViewModel(initialNozzleDistance, get()) }
    viewModel { NozzlePickerViewModel(get(), get(), get()) }
    viewModel { WorkViewModel() }
    viewModel { (initialScrewCount: Int) -> ScrewCountPickerViewModel(initialScrewCount, get()) }
    viewModel { (initialWheelRadius: Float) -> WheelRadiusPickerViewModel(initialWheelRadius, get()) }
}
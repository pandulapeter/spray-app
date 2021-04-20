package com.gyorgyzoltan.sprayApp.work.configuration

import android.os.Bundle
import android.view.View
import androidx.lifecycle.viewModelScope
import com.gyorgyzoltan.sprayApp.main.R
import com.gyorgyzoltan.sprayApp.main.shared.list.ListFragment
import com.gyorgyzoltan.sprayApp.main.shared.utilities.observeEvents
import com.gyorgyzoltan.sprayApp.work.configuration.list.ConfigurationAdapter
import com.gyorgyzoltan.sprayApp.work.configuration.list.ConfigurationListItem
import com.gyorgyzoltan.sprayApp.work.overview.WorkContainerFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class ConfigurationFragment : ListFragment<ConfigurationViewModel, ConfigurationListItem>(
    titleResourceId = R.string.configuration_title,
    subtitleResourceId = R.string.configuration_subtitle
) {

    override val viewModel by viewModel<ConfigurationViewModel>()

    override fun createAdapter() = ConfigurationAdapter(
        scope = viewModel.viewModelScope,
        onDoneButtonClicked = viewModel::onDoneButtonClicked,
        onNoSelectionItemClicked = viewModel::onNoSelectionItemClicked,
        onNozzleClicked = viewModel::onNozzleClicked
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.events.observeEvents(viewLifecycleOwner, ::handleEvent)
    }

    private fun handleEvent(event: ConfigurationViewModel.Event) = when (event) {
        ConfigurationViewModel.Event.NavigateToNozzlePicker -> navigateToNozzlePicker()
        ConfigurationViewModel.Event.NavigateToNozzleCountPicker -> navigateToNozzleCountPicker()
        ConfigurationViewModel.Event.NavigateToNozzleDistancePicker -> navigateToNozzleDistancePicker()
        ConfigurationViewModel.Event.NavigateToScrewCountPicker -> navigateToScrewCountPicker()
        ConfigurationViewModel.Event.NavigateToWheelRadiusPicker -> navigateToWheelRadiusPicker()
        ConfigurationViewModel.Event.CloseScreen -> closeScreen()
        ConfigurationViewModel.Event.ShowErrorSnackbar -> showErrorSnackbar()
    }

    private fun navigateToNozzlePicker() {
        (parentFragment as? WorkContainerFragment?)?.navigateToNozzlePicker()
    }

    private fun navigateToNozzleCountPicker() {
        (parentFragment as? WorkContainerFragment?)?.navigateToNozzleCountPicker()
    }

    private fun navigateToNozzleDistancePicker() {
        (parentFragment as? WorkContainerFragment?)?.navigateToNozzleDistancePicker()
    }

    private fun navigateToScrewCountPicker() {
        (parentFragment as? WorkContainerFragment?)?.navigateToScrewCountPicker()
    }

    private fun navigateToWheelRadiusPicker() {
        (parentFragment as? WorkContainerFragment?)?.navigateToWheelRadiusPicker()
    }

    private fun closeScreen() {
        (parentFragment as? WorkContainerFragment?)?.navigateToWork()
    }

    companion object {
        fun newInstance() = ConfigurationFragment()
    }
}
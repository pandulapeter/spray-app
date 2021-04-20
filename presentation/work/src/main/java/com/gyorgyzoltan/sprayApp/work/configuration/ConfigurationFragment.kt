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
        is ConfigurationViewModel.Event.NavigateToNozzleCountPicker -> navigateToNozzleCountPicker(event.currentNozzleCount)
        is ConfigurationViewModel.Event.NavigateToNozzleDistancePicker -> navigateToNozzleDistancePicker(event.currentNozzleDistance)
        is ConfigurationViewModel.Event.NavigateToScrewCountPicker -> navigateToScrewCountPicker(event.currentScrewCount)
        is ConfigurationViewModel.Event.NavigateToWheelRadiusPicker -> navigateToWheelRadiusPicker(event.currentWheelRadius)
        ConfigurationViewModel.Event.CloseScreen -> closeScreen()
        ConfigurationViewModel.Event.ShowErrorSnackbar -> showErrorSnackbar()
    }

    private fun navigateToNozzlePicker() {
        (parentFragment as? WorkContainerFragment?)?.navigateToNozzlePicker()
    }

    private fun navigateToNozzleCountPicker(currentNozzleCount: Int?) {
        (parentFragment as? WorkContainerFragment?)?.navigateToNozzleCountPicker(currentNozzleCount)
    }

    private fun navigateToNozzleDistancePicker(currentNozzleDistance: Float?) {
        (parentFragment as? WorkContainerFragment?)?.navigateToNozzleDistancePicker()
    }

    private fun navigateToScrewCountPicker(currentScrewCount: Int?) {
        (parentFragment as? WorkContainerFragment?)?.navigateToScrewCountPicker(currentScrewCount)
    }

    private fun navigateToWheelRadiusPicker(currentWheelRadius: Float?) {
        (parentFragment as? WorkContainerFragment?)?.navigateToWheelRadiusPicker(currentWheelRadius)
    }

    private fun closeScreen() {
        (parentFragment as? WorkContainerFragment?)?.navigateToWork()
    }

    companion object {
        fun newInstance() = ConfigurationFragment()
    }
}
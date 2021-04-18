package com.gyorgyzoltan.sprayApp.presentation.feature.main.work.configuration

import android.os.Bundle
import android.view.View
import androidx.lifecycle.viewModelScope
import com.gyorgyzoltan.sprayApp.presentation.R
import com.gyorgyzoltan.sprayApp.presentation.feature.main.work.WorkContainerFragment
import com.gyorgyzoltan.sprayApp.presentation.feature.main.work.configuration.list.ConfigurationAdapter
import com.gyorgyzoltan.sprayApp.presentation.feature.main.work.configuration.list.ConfigurationListItem
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.ListFragment
import com.gyorgyzoltan.sprayApp.presentation.utils.observeEvents
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class ConfigurationFragment : ListFragment<ConfigurationViewModel, ConfigurationListItem>(
    titleResourceId = R.string.configuration_title,
    subtitleResourceId = R.string.configuration_subtitle
) {

    override val viewModel by viewModel<ConfigurationViewModel>()

    override fun createAdapter() = ConfigurationAdapter(
        scope = viewModel.viewModelScope,
        onNozzleClicked = viewModel::onNozzleClicked,
        onDoneButtonClicked = viewModel::onDoneButtonClicked
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.events.observeEvents(viewLifecycleOwner) { event ->
            when (event) {
                ConfigurationViewModel.Event.NavigateToNozzlePicker -> navigateToNozzlePicker()
                ConfigurationViewModel.Event.CloseScreen -> closeScreen()
            }
        }
    }

    private fun navigateToNozzlePicker() {
        (parentFragment as? WorkContainerFragment?)?.navigateToNozzlePicker()
    }

    private fun closeScreen() {
        (parentFragment as? WorkContainerFragment?)?.navigateToWork()
    }

    companion object {
        fun newInstance() = ConfigurationFragment()
    }
}
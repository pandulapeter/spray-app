package com.gyorgyzoltan.sprayApp.feature.main.work.configuration

import android.os.Bundle
import android.view.View
import androidx.lifecycle.viewModelScope
import com.gyorgyzoltan.sprayApp.R
import com.gyorgyzoltan.sprayApp.feature.main.work.WorkContainerFragment
import com.gyorgyzoltan.sprayApp.feature.main.work.configuration.list.ConfigurationAdapter
import com.gyorgyzoltan.sprayApp.feature.main.work.configuration.list.ConfigurationListItem
import com.gyorgyzoltan.sprayApp.feature.shared.ListFragment
import com.gyorgyzoltan.sprayApp.utils.observeEvents
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConfigurationFragment : ListFragment<ConfigurationViewModel, ConfigurationListItem>(R.string.configuration_title) {

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

    override fun onResume() {
        super.onResume()
        viewModel.refreshItems()
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
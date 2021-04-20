package com.gyorgyzoltan.sprayApp.work.nozzleDistancePicker

import android.os.Bundle
import android.view.View
import androidx.lifecycle.viewModelScope
import com.gyorgyzoltan.sprayApp.main.R
import com.gyorgyzoltan.sprayApp.main.shared.list.ListFragment
import com.gyorgyzoltan.sprayApp.main.shared.utilities.observeEvents
import com.gyorgyzoltan.sprayApp.work.nozzleDistancePicker.list.NozzleDistancePickerAdapter
import com.gyorgyzoltan.sprayApp.work.nozzleDistancePicker.list.NozzleDistancePickerListItem
import com.gyorgyzoltan.sprayApp.work.overview.WorkContainerFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class NozzleDistancePickerFragment : ListFragment<NozzleDistancePickerViewModel, NozzleDistancePickerListItem>(
    titleResourceId = R.string.nozzle_distance_picker_title,
    subtitleResourceId = R.string.nozzle_distance_picker_subtitle
) {
    override val viewModel by viewModel<NozzleDistancePickerViewModel>()

    override fun createAdapter() = NozzleDistancePickerAdapter(
        scope = viewModel.viewModelScope
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.events.observeEvents(viewLifecycleOwner, ::handleEvent)
    }

    private fun handleEvent(event: NozzleDistancePickerViewModel.Event) = when (event) {
        NozzleDistancePickerViewModel.Event.CloseScreen -> closeScreen()
    }

    private fun closeScreen() {
        (parentFragment as? WorkContainerFragment?)?.navigateBack()
    }

    companion object {
        fun newInstance() = NozzleDistancePickerFragment()
    }
}
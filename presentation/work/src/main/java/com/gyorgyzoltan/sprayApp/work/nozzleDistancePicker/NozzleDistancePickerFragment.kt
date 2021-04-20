package com.gyorgyzoltan.sprayApp.work.nozzleDistancePicker

import android.os.Bundle
import android.view.View
import androidx.lifecycle.viewModelScope
import com.gyorgyzoltan.sprayApp.main.R
import com.gyorgyzoltan.sprayApp.main.shared.list.ListFragment
import com.gyorgyzoltan.sprayApp.main.shared.utilities.BundleArgumentDelegate
import com.gyorgyzoltan.sprayApp.main.shared.utilities.observeEvents
import com.gyorgyzoltan.sprayApp.main.shared.utilities.withArguments
import com.gyorgyzoltan.sprayApp.work.nozzleDistancePicker.list.NozzleDistancePickerAdapter
import com.gyorgyzoltan.sprayApp.work.nozzleDistancePicker.list.NozzleDistancePickerListItem
import com.gyorgyzoltan.sprayApp.work.overview.WorkContainerFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

internal class NozzleDistancePickerFragment : ListFragment<NozzleDistancePickerViewModel, NozzleDistancePickerListItem>(
    titleResourceId = R.string.nozzle_distance_picker_title,
    subtitleResourceId = R.string.nozzle_distance_picker_subtitle
) {
    override val viewModel by viewModel<NozzleDistancePickerViewModel> { parametersOf(arguments?.currentNozzleDistance) }

    override fun createAdapter() = NozzleDistancePickerAdapter(
        scope = viewModel.viewModelScope,
        onDoneButtonPressed = viewModel::onDoneButtonPressed
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
        private var Bundle.currentNozzleDistance by BundleArgumentDelegate.Float("currentNozzleDistance", NozzleDistancePickerViewModel.DEFAULT_NOZZLE_DISTANCE)

        fun newInstance(currentNozzleDistance: Float?) = NozzleDistancePickerFragment().withArguments {
            it.currentNozzleDistance = currentNozzleDistance ?: NozzleDistancePickerViewModel.DEFAULT_NOZZLE_DISTANCE
        }
    }
}
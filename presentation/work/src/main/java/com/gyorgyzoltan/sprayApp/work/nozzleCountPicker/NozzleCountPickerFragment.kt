package com.gyorgyzoltan.sprayApp.work.nozzleCountPicker

import android.os.Bundle
import android.view.View
import androidx.lifecycle.viewModelScope
import com.gyorgyzoltan.sprayApp.main.R
import com.gyorgyzoltan.sprayApp.main.shared.list.ListFragment
import com.gyorgyzoltan.sprayApp.main.shared.utilities.BundleArgumentDelegate
import com.gyorgyzoltan.sprayApp.main.shared.utilities.observeEvents
import com.gyorgyzoltan.sprayApp.main.shared.utilities.withArguments
import com.gyorgyzoltan.sprayApp.work.nozzleCountPicker.list.NozzleCountPickerAdapter
import com.gyorgyzoltan.sprayApp.work.nozzleCountPicker.list.NozzleCountPickerListItem
import com.gyorgyzoltan.sprayApp.work.overview.WorkContainerFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

internal class NozzleCountPickerFragment : ListFragment<NozzleCountPickerViewModel, NozzleCountPickerListItem>(
    titleResourceId = R.string.nozzle_count_picker_title,
    subtitleResourceId = R.string.nozzle_count_picker_subtitle
) {
    override val viewModel by viewModel<NozzleCountPickerViewModel> { parametersOf(arguments?.currentNozzleCount) }

    override fun createAdapter() = NozzleCountPickerAdapter(
        scope = viewModel.viewModelScope,
        onNozzleCountChanged = viewModel::onNozzleCountChanged,
        onDoneButtonPressed = viewModel::onDoneButtonPressed
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.events.observeEvents(viewLifecycleOwner, ::handleEvent)
    }

    private fun handleEvent(event: NozzleCountPickerViewModel.Event) = when (event) {
        NozzleCountPickerViewModel.Event.CloseScreen -> closeScreen()
    }

    private fun closeScreen() {
        (parentFragment as? WorkContainerFragment?)?.navigateBack()
    }

    companion object {
        private var Bundle.currentNozzleCount by BundleArgumentDelegate.Int("currentNozzleCount", NozzleCountPickerViewModel.DEFAULT_NOZZLE_COUNT)

        fun newInstance(currentNozzleCount: Int?) = NozzleCountPickerFragment().withArguments {
            it.currentNozzleCount = currentNozzleCount ?: NozzleCountPickerViewModel.DEFAULT_NOZZLE_COUNT
        }
    }
}
package com.gyorgyzoltan.sprayApp.work.nozzleCountPicker

import android.os.Bundle
import android.view.View
import androidx.lifecycle.viewModelScope
import com.gyorgyzoltan.sprayApp.main.R
import com.gyorgyzoltan.sprayApp.main.shared.list.ListFragment
import com.gyorgyzoltan.sprayApp.main.shared.utilities.observeEvents
import com.gyorgyzoltan.sprayApp.work.nozzleCountPicker.list.NozzleCountPickerAdapter
import com.gyorgyzoltan.sprayApp.work.nozzleCountPicker.list.NozzleCountPickerListItem
import com.gyorgyzoltan.sprayApp.work.overview.WorkContainerFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class NozzleCountPickerFragment : ListFragment<NozzleCountPickerViewModel, NozzleCountPickerListItem>(
    titleResourceId = R.string.nozzle_count_picker_title,
    subtitleResourceId = R.string.nozzle_count_picker_subtitle
) {

    override val viewModel by viewModel<NozzleCountPickerViewModel>()

    override fun createAdapter() = NozzleCountPickerAdapter(
        scope = viewModel.viewModelScope
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
        fun newInstance() = NozzleCountPickerFragment()
    }
}
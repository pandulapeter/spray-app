package com.gyorgyzoltan.sprayApp.feature.main.work.configuration.nozzlePicker

import android.os.Bundle
import android.view.View
import androidx.lifecycle.viewModelScope
import com.gyorgyzoltan.sprayApp.R
import com.gyorgyzoltan.sprayApp.feature.main.work.WorkContainerFragment
import com.gyorgyzoltan.sprayApp.feature.main.work.configuration.nozzlePicker.list.NozzlePickerAdapter
import com.gyorgyzoltan.sprayApp.feature.main.work.configuration.nozzlePicker.list.NozzlePickerListItem
import com.gyorgyzoltan.sprayApp.feature.shared.ListFragment
import com.gyorgyzoltan.sprayApp.utils.observeEvents
import org.koin.androidx.viewmodel.ext.android.viewModel

class NozzlePickerFragment : ListFragment<NozzlePickerViewModel, NozzlePickerListItem>(R.string.nozzle_picker_title) {

    override val viewModel by viewModel<NozzlePickerViewModel>()

    override fun createAdapter() = NozzlePickerAdapter(
        scope = viewModel.viewModelScope,
        onNozzleSelected = viewModel::onNozzleSelected
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.events.observeEvents(viewLifecycleOwner) { event ->
            when (event) {
                NozzlePickerViewModel.Event.CloseScreen -> closeScreen()
            }
        }
    }

    private fun closeScreen() {
        (parentFragment as? WorkContainerFragment?)?.navigateBack()
    }

    companion object {
        fun newInstance() = NozzlePickerFragment()
    }
}
package com.gyorgyzoltan.sprayApp.work.nozzlePicker

import android.os.Bundle
import android.view.View
import androidx.lifecycle.viewModelScope
import com.gyorgyzoltan.sprayApp.main.R
import com.gyorgyzoltan.sprayApp.main.shared.list.ListFragment
import com.gyorgyzoltan.sprayApp.main.shared.utilities.observeEvents
import com.gyorgyzoltan.sprayApp.work.nozzlePicker.list.NozzlePickerAdapter
import com.gyorgyzoltan.sprayApp.work.nozzlePicker.list.NozzlePickerListItem
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class NozzlePickerFragment : ListFragment<NozzlePickerViewModel, NozzlePickerListItem>(
    titleResourceId = R.string.nozzle_picker_title,
    subtitleResourceId = R.string.nozzle_picker_subtitle
) {

    override val viewModel by viewModel<NozzlePickerViewModel>()

    override fun createAdapter() = NozzlePickerAdapter(
        scope = viewModel.viewModelScope,
        onNozzleSelected = viewModel::onNozzleSelected,
        onNozzleTypeSelected = viewModel::onNozzleTypeSelected
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.events.observeEvents(viewLifecycleOwner, ::handleEvent)
    }

    override fun onBackPressed() = viewModel.onBackPressed()

    private fun handleEvent(event: NozzlePickerViewModel.Event) = when (event) {
        NozzlePickerViewModel.Event.ShowErrorSnackbar -> showErrorSnackbar()
        NozzlePickerViewModel.Event.CloseScreen -> closeScreen()
    }

    private fun closeScreen() {
        (parentFragment as? com.gyorgyzoltan.sprayApp.work.overview.WorkContainerFragment?)?.navigateBack()
    }

    companion object {
        fun newInstance() = NozzlePickerFragment()
    }
}
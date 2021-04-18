package com.gyorgyzoltan.sprayApp.presentation.feature.main.work.configuration.nozzlePicker

import android.os.Bundle
import android.view.View
import androidx.lifecycle.viewModelScope
import com.gyorgyzoltan.sprayApp.presentation.R
import com.gyorgyzoltan.sprayApp.presentation.feature.main.work.WorkContainerFragment
import com.gyorgyzoltan.sprayApp.presentation.feature.main.work.configuration.nozzlePicker.list.NozzlePickerAdapter
import com.gyorgyzoltan.sprayApp.presentation.feature.main.work.configuration.nozzlePicker.list.NozzlePickerListItem
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.ListFragment
import com.gyorgyzoltan.sprayApp.presentation.utils.observeEvents
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class NozzlePickerFragment : ListFragment<NozzlePickerViewModel, NozzlePickerListItem>(R.string.nozzle_picker_title) {

    override val viewModel by viewModel<NozzlePickerViewModel>()

    override fun createAdapter() = NozzlePickerAdapter(
        scope = viewModel.viewModelScope,
        onTryAgainButtonPressed = { viewModel.loadData(true) },
        onNozzleSelected = viewModel::onNozzleSelected,
        onNozzleTypeSelected = viewModel::onNozzleTypeSelected,
        onSeeAllTypesButtonPressed = { onBackPressed() }
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
        (parentFragment as? WorkContainerFragment?)?.navigateBack()
    }

    companion object {
        fun newInstance() = NozzlePickerFragment()
    }
}
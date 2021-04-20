package com.gyorgyzoltan.sprayApp.work.screwCountPicker

import android.os.Bundle
import android.view.View
import androidx.lifecycle.viewModelScope
import com.gyorgyzoltan.sprayApp.main.R
import com.gyorgyzoltan.sprayApp.main.shared.list.ListFragment
import com.gyorgyzoltan.sprayApp.main.shared.utilities.observeEvents
import com.gyorgyzoltan.sprayApp.work.overview.WorkContainerFragment
import com.gyorgyzoltan.sprayApp.work.screwCountPicker.list.ScrewCountPickerAdapter
import com.gyorgyzoltan.sprayApp.work.screwCountPicker.list.ScrewCountPickerListItem
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class ScrewCountPickerFragment : ListFragment<ScrewCountPickerViewModel, ScrewCountPickerListItem>(
    titleResourceId = R.string.screw_count_picker_title,
    subtitleResourceId = R.string.screw_count_picker_subtitle
) {
    override val viewModel by viewModel<ScrewCountPickerViewModel>()

    override fun createAdapter() = ScrewCountPickerAdapter(
        scope = viewModel.viewModelScope
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.events.observeEvents(viewLifecycleOwner, ::handleEvent)
    }

    private fun handleEvent(event: ScrewCountPickerViewModel.Event) = when (event) {
        ScrewCountPickerViewModel.Event.CloseScreen -> closeScreen()
    }

    private fun closeScreen() {
        (parentFragment as? WorkContainerFragment?)?.navigateBack()
    }

    companion object {
        fun newInstance() = ScrewCountPickerFragment()
    }
}
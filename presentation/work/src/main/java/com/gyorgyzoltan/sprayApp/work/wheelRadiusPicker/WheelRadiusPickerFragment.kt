package com.gyorgyzoltan.sprayApp.work.wheelRadiusPicker

import android.os.Bundle
import android.view.View
import androidx.lifecycle.viewModelScope
import com.gyorgyzoltan.sprayApp.main.R
import com.gyorgyzoltan.sprayApp.main.shared.list.ListFragment
import com.gyorgyzoltan.sprayApp.main.shared.utilities.observeEvents
import com.gyorgyzoltan.sprayApp.work.overview.WorkContainerFragment
import com.gyorgyzoltan.sprayApp.work.wheelRadiusPicker.list.WheelRadiusPickerAdapter
import com.gyorgyzoltan.sprayApp.work.wheelRadiusPicker.list.WheelRadiusPickerListItem
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class WheelRadiusPickerFragment : ListFragment<WheelRadiusPickerViewModel, WheelRadiusPickerListItem>(
    titleResourceId = R.string.wheel_radius_picker_title,
    subtitleResourceId = R.string.wheel_radius_picker_subtitle
) {

    override val viewModel by viewModel<WheelRadiusPickerViewModel>()

    override fun createAdapter() = WheelRadiusPickerAdapter(
        scope = viewModel.viewModelScope
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.events.observeEvents(viewLifecycleOwner, ::handleEvent)
    }

    private fun handleEvent(event: WheelRadiusPickerViewModel.Event) = when (event) {
        WheelRadiusPickerViewModel.Event.CloseScreen -> closeScreen()
    }

    private fun closeScreen() {
        (parentFragment as? WorkContainerFragment?)?.navigateBack()
    }

    companion object {
        fun newInstance() = WheelRadiusPickerFragment()
    }
}
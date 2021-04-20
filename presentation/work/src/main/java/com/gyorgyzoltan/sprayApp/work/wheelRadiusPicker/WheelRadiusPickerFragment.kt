package com.gyorgyzoltan.sprayApp.work.wheelRadiusPicker

import android.os.Bundle
import android.view.View
import androidx.lifecycle.viewModelScope
import com.gyorgyzoltan.sprayApp.main.R
import com.gyorgyzoltan.sprayApp.main.shared.list.ListFragment
import com.gyorgyzoltan.sprayApp.main.shared.utilities.BundleArgumentDelegate
import com.gyorgyzoltan.sprayApp.main.shared.utilities.observeEvents
import com.gyorgyzoltan.sprayApp.main.shared.utilities.withArguments
import com.gyorgyzoltan.sprayApp.work.overview.WorkContainerFragment
import com.gyorgyzoltan.sprayApp.work.wheelRadiusPicker.list.WheelRadiusPickerAdapter
import com.gyorgyzoltan.sprayApp.work.wheelRadiusPicker.list.WheelRadiusPickerListItem
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

internal class WheelRadiusPickerFragment : ListFragment<WheelRadiusPickerViewModel, WheelRadiusPickerListItem>(
    titleResourceId = R.string.wheel_radius_picker_title,
    subtitleResourceId = R.string.wheel_radius_picker_subtitle
) {
    override val viewModel by viewModel<WheelRadiusPickerViewModel> { parametersOf(arguments?.currentWheelRadius) }

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
        private var Bundle.currentWheelRadius by BundleArgumentDelegate.Float("currentWheelRadius", WheelRadiusPickerViewModel.DEFAULT_WHEEL_RADIUS)

        fun newInstance(currentWheelRadius: Float?) = WheelRadiusPickerFragment().withArguments {
            it.currentWheelRadius = currentWheelRadius ?: WheelRadiusPickerViewModel.DEFAULT_WHEEL_RADIUS
        }
    }
}
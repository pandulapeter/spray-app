package com.gyorgyzoltan.sprayApp.work.wheelRadiusPicker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gyorgyzoltan.sprayApp.domain.configuration.SetWheelRadiusUseCase
import com.gyorgyzoltan.sprayApp.main.shared.list.ListViewModel
import com.gyorgyzoltan.sprayApp.main.shared.utilities.Consumable
import com.gyorgyzoltan.sprayApp.work.wheelRadiusPicker.list.WheelRadiusPickerListItem

internal class WheelRadiusPickerViewModel(
    private val setWheelRadius: SetWheelRadiusUseCase
) : ListViewModel<WheelRadiusPickerListItem>() {

    override val items = MutableLiveData(emptyList<WheelRadiusPickerListItem>())
    private val _events = MutableLiveData<Consumable<Event>>()
    val events: LiveData<Consumable<Event>> = _events

    sealed class Event {
        object CloseScreen : Event()
    }
}
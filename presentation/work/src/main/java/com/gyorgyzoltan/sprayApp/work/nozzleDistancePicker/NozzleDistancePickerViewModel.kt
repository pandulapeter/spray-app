package com.gyorgyzoltan.sprayApp.work.nozzleDistancePicker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gyorgyzoltan.sprayApp.domain.configuration.SetNozzleDistanceUseCase
import com.gyorgyzoltan.sprayApp.main.shared.list.ListViewModel
import com.gyorgyzoltan.sprayApp.main.shared.utilities.Consumable
import com.gyorgyzoltan.sprayApp.work.nozzleDistancePicker.list.NozzleDistancePickerListItem

internal class NozzleDistancePickerViewModel(
    private val setNozzleDistance: SetNozzleDistanceUseCase
) : ListViewModel<NozzleDistancePickerListItem>() {

    override val items = MutableLiveData(emptyList<NozzleDistancePickerListItem>())
    private val _events = MutableLiveData<Consumable<Event>>()
    val events: LiveData<Consumable<Event>> = _events

    sealed class Event {
        object CloseScreen : Event()
    }
}
package com.gyorgyzoltan.sprayApp.work.nozzleCountPicker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gyorgyzoltan.sprayApp.domain.configuration.SetNozzleCountUseCase
import com.gyorgyzoltan.sprayApp.main.shared.list.ListViewModel
import com.gyorgyzoltan.sprayApp.main.shared.utilities.Consumable
import com.gyorgyzoltan.sprayApp.work.nozzleCountPicker.list.NozzleCountPickerListItem

internal class NozzleCountPickerViewModel(
    private val setNozzleCount: SetNozzleCountUseCase
) : ListViewModel<NozzleCountPickerListItem>() {

    override val items = MutableLiveData(emptyList<NozzleCountPickerListItem>())
    private val _events = MutableLiveData<Consumable<Event>>()
    val events: LiveData<Consumable<Event>> = _events

    sealed class Event {
        object CloseScreen : Event()
    }
}
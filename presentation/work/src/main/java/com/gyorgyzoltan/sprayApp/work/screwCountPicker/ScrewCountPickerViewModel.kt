package com.gyorgyzoltan.sprayApp.work.screwCountPicker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gyorgyzoltan.sprayApp.domain.configuration.SetScrewCountUseCase
import com.gyorgyzoltan.sprayApp.main.shared.list.ListViewModel
import com.gyorgyzoltan.sprayApp.main.shared.utilities.Consumable
import com.gyorgyzoltan.sprayApp.work.screwCountPicker.list.ScrewCountPickerListItem

internal class ScrewCountPickerViewModel(
    private val setScrewCount: SetScrewCountUseCase
) : ListViewModel<ScrewCountPickerListItem>() {

    override val items = MutableLiveData(emptyList<ScrewCountPickerListItem>())
    private val _events = MutableLiveData<Consumable<Event>>()
    val events: LiveData<Consumable<Event>> = _events

    sealed class Event {
        object CloseScreen : Event()
    }
}
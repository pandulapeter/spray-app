package com.gyorgyzoltan.sprayApp.feature.main.work.configuration.nozzlePicker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gyorgyzoltan.sprayApp.R
import com.gyorgyzoltan.sprayApp.data.PreferenceManager
import com.gyorgyzoltan.sprayApp.data.model.Nozzle
import com.gyorgyzoltan.sprayApp.feature.main.work.configuration.nozzlePicker.list.NozzlePickerListItem
import com.gyorgyzoltan.sprayApp.feature.main.work.configuration.nozzlePicker.list.NozzleViewHolder
import com.gyorgyzoltan.sprayApp.feature.shared.ListViewModel
import com.gyorgyzoltan.sprayApp.feature.shared.list.TextViewHolder
import com.gyorgyzoltan.sprayApp.utils.Consumable

class NozzlePickerViewModel(
    private val preferenceManager: PreferenceManager
) : ListViewModel<NozzlePickerListItem>() {

    override val items: LiveData<List<NozzlePickerListItem>> = MutableLiveData(
        listOf(
            TextViewHolder.UiModel(R.string.nozzle_picker_hint)
        ) + Nozzle.values().map { nozzle ->
            NozzleViewHolder.UiModel(
                nozzle = nozzle,
                isSelected = preferenceManager.selectedNozzleName == nozzle.name
            )
        }
    )
    private val _events = MutableLiveData<Consumable<Event>>()
    val events: LiveData<Consumable<Event>> = _events

    fun onNozzleSelected(nozzle: Nozzle) {
        preferenceManager.selectedNozzleName = nozzle.name
        _events.value = Consumable(Event.CloseScreen)
    }

    sealed class Event {
        object CloseScreen : Event()
    }
}
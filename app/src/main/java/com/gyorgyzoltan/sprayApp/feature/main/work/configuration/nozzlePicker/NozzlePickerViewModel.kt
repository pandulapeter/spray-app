package com.gyorgyzoltan.sprayApp.feature.main.work.configuration.nozzlePicker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.gyorgyzoltan.sprayApp.R
import com.gyorgyzoltan.sprayApp.data.PreferenceManager
import com.gyorgyzoltan.sprayApp.data.model.Nozzle
import com.gyorgyzoltan.sprayApp.data.repository.NozzleRepository
import com.gyorgyzoltan.sprayApp.feature.main.work.configuration.nozzlePicker.list.NozzlePickerListItem
import com.gyorgyzoltan.sprayApp.feature.main.work.configuration.nozzlePicker.list.NozzleViewHolder
import com.gyorgyzoltan.sprayApp.feature.shared.ListViewModel
import com.gyorgyzoltan.sprayApp.feature.shared.list.TextViewHolder
import com.gyorgyzoltan.sprayApp.utils.Consumable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NozzlePickerViewModel(
    private val preferenceManager: PreferenceManager,
    private val nozzleRepository: NozzleRepository
) : ListViewModel<NozzlePickerListItem>() {

    private val _items = MutableStateFlow(
        listOf<NozzlePickerListItem>(
            TextViewHolder.UiModel(R.string.nozzle_picker_loading)
        )
    )
    override val items = _items.asLiveData()
    private val _events = MutableLiveData<Consumable<Event>>()
    val events: LiveData<Consumable<Event>> = _events

    init {
        viewModelScope.launch {
            _items.value = listOf(
                TextViewHolder.UiModel(R.string.nozzle_picker_hint)
            ) + nozzleRepository.getNozzles().map { nozzle ->
                NozzleViewHolder.UiModel(
                    nozzle = nozzle,
                    isSelected = preferenceManager.selectedNozzleName == nozzle.name
                )
            }
        }
    }

    fun onNozzleSelected(nozzle: Nozzle) {
        preferenceManager.selectedNozzleName = nozzle.name.orEmpty()
        _events.value = Consumable(Event.CloseScreen)
    }

    sealed class Event {
        object CloseScreen : Event()
    }
}
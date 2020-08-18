package com.gyorgyzoltan.sprayApp.feature.main.work.configuration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gyorgyzoltan.sprayApp.R
import com.gyorgyzoltan.sprayApp.data.PreferenceManager
import com.gyorgyzoltan.sprayApp.data.model.Nozzle
import com.gyorgyzoltan.sprayApp.feature.main.work.configuration.list.ConfigurationListItem
import com.gyorgyzoltan.sprayApp.feature.main.work.configuration.list.DoneButtonViewHolder
import com.gyorgyzoltan.sprayApp.feature.main.work.configuration.list.SelectedNozzleViewHolder
import com.gyorgyzoltan.sprayApp.feature.shared.ListViewModel
import com.gyorgyzoltan.sprayApp.feature.shared.list.TextViewHolder
import com.gyorgyzoltan.sprayApp.utils.Consumable

class ConfigurationViewModel(
    private val preferenceManager: PreferenceManager
) : ListViewModel<ConfigurationListItem>() {

    private val _items = MutableLiveData<List<ConfigurationListItem>>()
    override val items: LiveData<List<ConfigurationListItem>> = _items
    private val _events = MutableLiveData<Consumable<Event>>()
    val events: LiveData<Consumable<Event>> = _events

    fun refreshItems() {
        _items.value = listOf(
            TextViewHolder.UiModel(R.string.configuration_placeholder),
            SelectedNozzleViewHolder.UiModel(Nozzle.values().firstOrNull { it.name == preferenceManager.selectedNozzleName }),
            DoneButtonViewHolder.UiModel(preferenceManager.selectedNozzleName.isNotBlank())
        )
    }

    fun onDoneButtonClicked() {
        preferenceManager.isConfigurationSet = true
        _events.value = Consumable(Event.CloseScreen)
    }

    fun onNozzleClicked() {
        _events.value = Consumable(Event.NavigateToNozzlePicker)
    }

    sealed class Event {
        object NavigateToNozzlePicker : Event()
        object CloseScreen : Event()
    }
}
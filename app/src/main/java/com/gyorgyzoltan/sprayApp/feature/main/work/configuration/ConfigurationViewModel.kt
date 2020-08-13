package com.gyorgyzoltan.sprayApp.feature.main.work.configuration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gyorgyzoltan.sprayApp.R
import com.gyorgyzoltan.sprayApp.data.PreferenceManager
import com.gyorgyzoltan.sprayApp.feature.main.work.configuration.list.ConfigurationListItem
import com.gyorgyzoltan.sprayApp.feature.main.work.configuration.list.DoneButtonViewHolder
import com.gyorgyzoltan.sprayApp.feature.shared.ListViewModel
import com.gyorgyzoltan.sprayApp.feature.shared.list.TextViewHolder
import com.gyorgyzoltan.sprayApp.utils.Consumable

class ConfigurationViewModel(
    private val preferenceManager: PreferenceManager
) : ListViewModel<ConfigurationListItem>() {

    override val items: LiveData<List<ConfigurationListItem>> = MutableLiveData(
        listOf(
            TextViewHolder.UiModel(R.string.configuration_placeholder),
            DoneButtonViewHolder.UiModel()
        )
    )
    private val _events = MutableLiveData<Consumable<Event>>()
    val events: LiveData<Consumable<Event>> = _events

    fun onDoneButtonClicked() {
        preferenceManager.isConfigurationSet = true
        _events.value = Consumable(Event.CloseScreen)
    }

    sealed class Event {
        object CloseScreen : Event()
    }
}
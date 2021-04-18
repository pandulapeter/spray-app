package com.gyorgyzoltan.sprayApp.work.configuration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.gyorgyzoltan.sprayApp.main.R
import com.gyorgyzoltan.sprayApp.main.shared.list.ListViewModel
import com.gyorgyzoltan.sprayApp.main.shared.utilities.Consumable
import com.gyorgyzoltan.sprayApp.work.configuration.list.ConfigurationDoneButtonViewHolder
import com.gyorgyzoltan.sprayApp.work.configuration.list.ConfigurationListItem
import com.gyorgyzoltan.sprayApp.work.configuration.list.ConfigurationSelectedNozzleViewHolder
import com.gyorgyzoltan.sprayApp.work.configuration.list.ConfigurationTextViewHolder
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

internal class ConfigurationViewModel : ListViewModel<ConfigurationListItem>(true) {

    private val _items = MutableStateFlow(emptyList<ConfigurationListItem>())
    override val items = _items.asLiveData()
    private val _events = MutableLiveData<Consumable<Event>>()
    val events: LiveData<Consumable<Event>> = _events
    private val _shouldShowLoadingIndicator = MutableStateFlow(false)
    override val shouldShowLoadingIndicator = _shouldShowLoadingIndicator.asLiveData()

    init {
        loadData(false)
    }

    override fun loadData(isForceRefresh: Boolean) {
        viewModelScope.launch {
            _shouldShowLoadingIndicator.value = true
            _items.value = listOf(
                ConfigurationTextViewHolder.UiModel(R.string.configuration_selected_nozzle),
                ConfigurationSelectedNozzleViewHolder.UiModel(null),
                ConfigurationTextViewHolder.UiModel(R.string.configuration_wheel_radius),
                ConfigurationTextViewHolder.UiModel(R.string.configuration_screw_count),
                ConfigurationTextViewHolder.UiModel(R.string.configuration_nozzle_count),
                ConfigurationTextViewHolder.UiModel(R.string.configuration_nozzle_distance),
                ConfigurationDoneButtonViewHolder.UiModel(false)
            )
            delay(200)
            _shouldShowLoadingIndicator.value = false
        }
    }

    fun onDoneButtonClicked() {
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
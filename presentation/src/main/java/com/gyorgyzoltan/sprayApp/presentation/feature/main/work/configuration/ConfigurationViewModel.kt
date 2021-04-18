package com.gyorgyzoltan.sprayApp.presentation.feature.main.work.configuration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.gyorgyzoltan.sprayApp.presentation.R
import com.gyorgyzoltan.sprayApp.presentation.feature.main.work.configuration.list.ConfigurationListItem
import com.gyorgyzoltan.sprayApp.presentation.feature.main.work.configuration.list.DoneButtonViewHolder
import com.gyorgyzoltan.sprayApp.presentation.feature.main.work.configuration.list.SelectedNozzleViewHolder
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.ListViewModel
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.list.TextViewHolder
import com.gyorgyzoltan.sprayApp.utils.Consumable
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

internal class ConfigurationViewModel : ListViewModel<ConfigurationListItem>(true) {

    private val _items = MutableStateFlow(emptyList<ConfigurationListItem>())
    override val items = _items.asLiveData()
    private val _events = MutableLiveData<Consumable<Event>>()
    val events: LiveData<Consumable<Event>> = _events
    private val _isLoading = MutableStateFlow(false)
    override val isLoading = _isLoading.asLiveData()

    init {
        loadData(false)
    }

    override fun loadData(isForceRefresh: Boolean) {
        viewModelScope.launch {
            _isLoading.value = true
            _items.value = listOf(
                TextViewHolder.UiModel(R.string.configuration_selected_nozzle),
                SelectedNozzleViewHolder.UiModel(null),
                TextViewHolder.UiModel(R.string.configuration_wheel_radius),
                TextViewHolder.UiModel(R.string.configuration_screw_count),
                TextViewHolder.UiModel(R.string.configuration_nozzle_count),
                TextViewHolder.UiModel(R.string.configuration_nozzle_distance),
                DoneButtonViewHolder.UiModel(false)
            )
            delay(200)
            _isLoading.value = false
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
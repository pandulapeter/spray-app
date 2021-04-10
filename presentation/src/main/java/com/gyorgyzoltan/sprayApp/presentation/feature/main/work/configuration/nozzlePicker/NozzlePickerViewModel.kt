package com.gyorgyzoltan.sprayApp.presentation.feature.main.work.configuration.nozzlePicker

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.list.TextViewHolder
import com.gyorgyzoltan.sprayApp.model.nozzle.Nozzle
import com.gyorgyzoltan.sprayApp.presentation.R
import com.gyorgyzoltan.sprayApp.presentation.feature.main.work.configuration.nozzlePicker.list.NozzlePickerListItem
import com.gyorgyzoltan.sprayApp.presentation.feature.main.work.configuration.nozzlePicker.list.NozzleViewHolder
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.ListViewModel
import com.gyorgyzoltan.sprayApp.repository.preferences.PreferenceManager
import com.gyorgyzoltan.sprayApp.repository.repository.nozzle.NozzleRepository
import com.gyorgyzoltan.sprayApp.utils.Consumable
import com.gyorgyzoltan.sprayApp.presentation.utils.NozzleUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

internal class NozzlePickerViewModel(
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
        preferenceManager.selectedNozzleName = nozzle.name
        _events.value = Consumable(Event.CloseScreen)
        Log.d(
            "DEBBBB", "Pressure = ${
                try {
                    NozzleUtils.calculatePressure(0.36f, nozzle)
                } catch (_: Exception) {
                    "error"
                }
            }"
        )
    }

    sealed class Event {
        object CloseScreen : Event()
    }
}
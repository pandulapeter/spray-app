package com.gyorgyzoltan.sprayApp.work.nozzleDistancePicker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.gyorgyzoltan.sprayApp.domain.configuration.SetNozzleDistanceUseCase
import com.gyorgyzoltan.sprayApp.main.shared.list.ListViewModel
import com.gyorgyzoltan.sprayApp.main.shared.utilities.Consumable
import com.gyorgyzoltan.sprayApp.work.nozzleDistancePicker.list.NozzleDistanceDoneButtonViewHolder
import com.gyorgyzoltan.sprayApp.work.nozzleDistancePicker.list.NozzleDistanceHintViewHolder
import com.gyorgyzoltan.sprayApp.work.nozzleDistancePicker.list.NozzleDistancePickerListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

internal class NozzleDistancePickerViewModel(
    initialNozzleDistance: Float,
    private val setNozzleDistance: SetNozzleDistanceUseCase
) : ListViewModel<NozzleDistancePickerListItem>() {

    private val nozzleDistance = MutableStateFlow(initialNozzleDistance)
    override val items = nozzleDistance.map { nozzleDistance ->
        listOf(
            NozzleDistanceHintViewHolder.UiModel(),
            NozzleDistanceDoneButtonViewHolder.UiModel(nozzleDistance.isValidNozzleDistance)
        )
    }.asLiveData()
    private val _events = MutableLiveData<Consumable<Event>>()
    val events: LiveData<Consumable<Event>> = _events

    fun onDoneButtonPressed() {
        nozzleDistance.value.let { nozzleDistance ->
            if (nozzleDistance.isValidNozzleDistance) {
                setNozzleDistance(nozzleDistance)
                _events.value = Consumable(Event.CloseScreen)
            }
        }
    }

    private val Float.isValidNozzleDistance get() = this > MINIMUM_NOZZLE_DISTANCE

    sealed class Event {
        object CloseScreen : Event()
    }

    companion object {
        const val DEFAULT_NOZZLE_DISTANCE = 0f
        const val MINIMUM_NOZZLE_DISTANCE = 0f
    }
}
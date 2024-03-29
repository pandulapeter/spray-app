package com.gyorgyzoltan.sprayApp.work.nozzleDistancePicker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.gyorgyzoltan.sprayApp.domain.configuration.SetNozzleDistanceUseCase
import com.gyorgyzoltan.sprayApp.main.shared.list.ListViewModel
import com.gyorgyzoltan.sprayApp.main.shared.utilities.Consumable
import com.gyorgyzoltan.sprayApp.work.nozzleDistancePicker.list.NozzleDistanceDoneButtonViewHolder
import com.gyorgyzoltan.sprayApp.work.nozzleDistancePicker.list.NozzleDistanceHintViewHolder
import com.gyorgyzoltan.sprayApp.work.nozzleDistancePicker.list.NozzleDistanceInputViewHolder
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
            NozzleDistanceInputViewHolder.UiModel(nozzleDistance),
            NozzleDistanceDoneButtonViewHolder.UiModel(nozzleDistance.isValidNozzleDistance)
        )
    }.asLiveData()
    private val _events = MutableLiveData<Consumable<Event>>()
    val events: LiveData<Consumable<Event>> = _events

    fun onNozzleDistanceChanged(newNozzleDistance: Float) {
        nozzleDistance.value = newNozzleDistance
    }

    fun onDoneButtonPressed() {
        nozzleDistance.value.let { nozzleDistance ->
            if (nozzleDistance.isValidNozzleDistance) {
                setNozzleDistance(nozzleDistance)
                _events.value = Consumable(Event.CloseScreen)
            }
        }
    }

    private val Float.isValidNozzleDistance get() = this in MINIMUM_NOZZLE_DISTANCE..MAXIMUM_NOZZLE_DISTANCE

    sealed class Event {
        object CloseScreen : Event()
    }

    companion object {
        const val DEFAULT_NOZZLE_DISTANCE = 50f
        const val MINIMUM_NOZZLE_DISTANCE = 10f
        const val MAXIMUM_NOZZLE_DISTANCE = 200f
    }
}
package com.gyorgyzoltan.sprayApp.work.nozzleCountPicker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.gyorgyzoltan.sprayApp.domain.configuration.SetNozzleCountUseCase
import com.gyorgyzoltan.sprayApp.main.shared.list.ListViewModel
import com.gyorgyzoltan.sprayApp.main.shared.utilities.Consumable
import com.gyorgyzoltan.sprayApp.work.nozzleCountPicker.list.NozzleCountDoneButtonViewHolder
import com.gyorgyzoltan.sprayApp.work.nozzleCountPicker.list.NozzleCountHintViewHolder
import com.gyorgyzoltan.sprayApp.work.nozzleCountPicker.list.NozzleCountPickerListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

internal class NozzleCountPickerViewModel(
    initialNozzleCount: Int,
    private val setNozzleCount: SetNozzleCountUseCase
) : ListViewModel<NozzleCountPickerListItem>() {

    private val nozzleCount = MutableStateFlow(initialNozzleCount)
    override val items = nozzleCount.map { nozzleCount ->
        listOf(
            NozzleCountHintViewHolder.UiModel(),
            NozzleCountDoneButtonViewHolder.UiModel(nozzleCount.isValidNozzleCount)
        )
    }.asLiveData()
    private val _events = MutableLiveData<Consumable<Event>>()
    val events: LiveData<Consumable<Event>> = _events

    fun onDoneButtonPressed() {
        nozzleCount.value.let { nozzleCount ->
            if (nozzleCount.isValidNozzleCount) {
                setNozzleCount(nozzleCount)
                _events.value = Consumable(Event.CloseScreen)
            }
        }
    }

    private val Int.isValidNozzleCount get() = this in MINIMUM_NOZZLE_COUNT..MAXIMUM_NOZZLE_COUNT

    sealed class Event {
        object CloseScreen : Event()
    }

    companion object {
        const val DEFAULT_NOZZLE_COUNT = 8
        const val MINIMUM_NOZZLE_COUNT = 1
        const val MAXIMUM_NOZZLE_COUNT = 64
    }
}
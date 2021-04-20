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
    override val items = nozzleCount.map {
        listOf(
            NozzleCountHintViewHolder.UiModel(),
            NozzleCountDoneButtonViewHolder.UiModel(isNozzleCountValid())
        )
    }.asLiveData()
    private val _events = MutableLiveData<Consumable<Event>>()
    val events: LiveData<Consumable<Event>> = _events

    fun onDoneButtonPressed() {
        if (isNozzleCountValid()) {
            setNozzleCount(nozzleCount.value)
        }
    }

    private fun isNozzleCountValid() = nozzleCount.value > 0

    sealed class Event {
        object CloseScreen : Event()
    }

    companion object {
        const val DEFAULT_NOZZLE_COUNT = 0
    }
}
package com.gyorgyzoltan.sprayApp.work.screwCountPicker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.gyorgyzoltan.sprayApp.domain.configuration.SetScrewCountUseCase
import com.gyorgyzoltan.sprayApp.main.shared.list.ListViewModel
import com.gyorgyzoltan.sprayApp.main.shared.utilities.Consumable
import com.gyorgyzoltan.sprayApp.work.screwCountPicker.list.ScrewCountDoneButtonViewHolder
import com.gyorgyzoltan.sprayApp.work.screwCountPicker.list.ScrewCountHintViewHolder
import com.gyorgyzoltan.sprayApp.work.screwCountPicker.list.ScrewCountPickerListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

internal class ScrewCountPickerViewModel(
    initialScrewCount: Int,
    private val setScrewCount: SetScrewCountUseCase
) : ListViewModel<ScrewCountPickerListItem>() {

    private val screwCount = MutableStateFlow(initialScrewCount)
    override val items = screwCount.map { screwCount ->
        listOf(
            ScrewCountHintViewHolder.UiModel(),
            ScrewCountDoneButtonViewHolder.UiModel(isScrewCountValid())
        )
    }.asLiveData()
    private val _events = MutableLiveData<Consumable<Event>>()
    val events: LiveData<Consumable<Event>> = _events

    fun onDoneButtonPressed() {
        if (isScrewCountValid()) {
            setScrewCount(screwCount.value)
        }
    }

    private fun isScrewCountValid() = screwCount.value > 0

    sealed class Event {
        object CloseScreen : Event()
    }

    companion object {
        const val DEFAULT_SCREW_COUNT = 0
    }
}
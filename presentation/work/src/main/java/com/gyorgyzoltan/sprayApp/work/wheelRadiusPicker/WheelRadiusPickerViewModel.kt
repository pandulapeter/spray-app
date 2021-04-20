package com.gyorgyzoltan.sprayApp.work.wheelRadiusPicker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.gyorgyzoltan.sprayApp.domain.configuration.SetWheelRadiusUseCase
import com.gyorgyzoltan.sprayApp.main.shared.list.ListViewModel
import com.gyorgyzoltan.sprayApp.main.shared.utilities.Consumable
import com.gyorgyzoltan.sprayApp.work.wheelRadiusPicker.list.WheelRadiusHintViewHolder
import com.gyorgyzoltan.sprayApp.work.wheelRadiusPicker.list.WheelRadiusPickerListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

internal class WheelRadiusPickerViewModel(
    initialWheelRadius: Float,
    private val setWheelRadius: SetWheelRadiusUseCase
) : ListViewModel<WheelRadiusPickerListItem>() {

    private val wheelRadius = MutableStateFlow(initialWheelRadius)
    override val items = wheelRadius.map { wheelRadius ->
        listOf<WheelRadiusPickerListItem>(
            WheelRadiusHintViewHolder.UiModel()
        )
    }.asLiveData()
    private val _events = MutableLiveData<Consumable<Event>>()
    val events: LiveData<Consumable<Event>> = _events

    fun onDoneButtonPressed() {
        setWheelRadius(wheelRadius.value)
    }

    sealed class Event {
        object CloseScreen : Event()
    }

    companion object {
        const val DEFAULT_WHEEL_RADIUS = 0f
    }
}
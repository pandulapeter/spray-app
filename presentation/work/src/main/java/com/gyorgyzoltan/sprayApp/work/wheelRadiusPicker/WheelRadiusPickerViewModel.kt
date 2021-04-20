package com.gyorgyzoltan.sprayApp.work.wheelRadiusPicker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.gyorgyzoltan.sprayApp.domain.configuration.SetWheelRadiusUseCase
import com.gyorgyzoltan.sprayApp.main.shared.list.ListViewModel
import com.gyorgyzoltan.sprayApp.main.shared.utilities.Consumable
import com.gyorgyzoltan.sprayApp.work.wheelRadiusPicker.list.WheelRadiusDoneButtonViewHolder
import com.gyorgyzoltan.sprayApp.work.wheelRadiusPicker.list.WheelRadiusHintViewHolder
import com.gyorgyzoltan.sprayApp.work.wheelRadiusPicker.list.WheelRadiusInputViewHolder
import com.gyorgyzoltan.sprayApp.work.wheelRadiusPicker.list.WheelRadiusPickerListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

internal class WheelRadiusPickerViewModel(
    initialWheelRadius: Float,
    private val setWheelRadius: SetWheelRadiusUseCase
) : ListViewModel<WheelRadiusPickerListItem>() {

    private val wheelRadius = MutableStateFlow(initialWheelRadius)
    override val items = wheelRadius.map { wheelRadius ->
        listOf(
            WheelRadiusHintViewHolder.UiModel(),
            WheelRadiusInputViewHolder.UiModel(wheelRadius),
            WheelRadiusDoneButtonViewHolder.UiModel(wheelRadius.isValidWheelRadius)
        )
    }.asLiveData()
    private val _events = MutableLiveData<Consumable<Event>>()
    val events: LiveData<Consumable<Event>> = _events

    fun onWheelRadiusChanged(newWheelRadius: Float) {
        wheelRadius.value = newWheelRadius
    }

    fun onDoneButtonPressed() {
        wheelRadius.value.let { wheelRadius ->
            if (wheelRadius.isValidWheelRadius) {
                setWheelRadius(wheelRadius)
                _events.value = Consumable(Event.CloseScreen)
            }
        }
    }

    private val Float.isValidWheelRadius get() = this > MINIMUM_WHEEL_RADIUS

    sealed class Event {
        object CloseScreen : Event()
    }

    companion object {
        const val DEFAULT_WHEEL_RADIUS = 0f
        const val MINIMUM_WHEEL_RADIUS = 0f
        const val MAXIMUM_WHEEL_RADIUS = 200f
    }
}
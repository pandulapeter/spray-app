package com.gyorgyzoltan.sprayApp.presentation.feature.main.work.configuration.nozzlePicker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.gyorgyzoltan.sprayApp.domain.nozzle.NozzleTypesUseCase
import com.gyorgyzoltan.sprayApp.domain.nozzle.RefreshNozzleTypesUseCase
import com.gyorgyzoltan.sprayApp.model.DataState
import com.gyorgyzoltan.sprayApp.model.nozzle.Nozzle
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleType
import com.gyorgyzoltan.sprayApp.presentation.R
import com.gyorgyzoltan.sprayApp.presentation.feature.main.work.configuration.nozzlePicker.list.NozzlePickerListItem
import com.gyorgyzoltan.sprayApp.presentation.feature.main.work.configuration.nozzlePicker.list.NozzleTypeViewHolder
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.ListViewModel
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.list.TextViewHolder
import com.gyorgyzoltan.sprayApp.utils.Consumable
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

internal class NozzlePickerViewModel(
    nozzleTypes: NozzleTypesUseCase,
    private val refreshNozzleTypes: RefreshNozzleTypesUseCase,
) : ListViewModel<NozzlePickerListItem>(true) {

    override val items = nozzleTypes().map { it.toItems() }.asLiveData()
    override val isLoading = nozzleTypes().map { it is DataState.Loading }.asLiveData()
    private val _events = MutableLiveData<Consumable<Event>>()
    val events: LiveData<Consumable<Event>> = _events

    init {
        loadData(false)
    }

    override fun loadData(isForceRefresh: Boolean) {
        viewModelScope.launch { refreshNozzleTypes() }
    }

    fun onNozzleSelected(nozzle: Nozzle) {
        _events.value = Consumable(Event.CloseScreen)
    }

    fun onNozzleTypeSelected(nozzleType: NozzleType) {
        _events.value = Consumable(Event.CloseScreen)
    }

    private fun DataState<List<NozzleType>>.toItems() = mutableListOf<NozzlePickerListItem>().apply {
        data.let { nozzleTypes ->
            when {
                nozzleTypes?.isNotEmpty() == true -> {
                    add(TextViewHolder.UiModel(R.string.nozzle_picker_hint))
                    addAll(nozzleTypes.map { NozzleTypeViewHolder.UiModel(it, false) })
                }
                nozzleTypes == null -> if (this is DataState.Error<*>) {
                    add(TextViewHolder.UiModel(R.string.general_error))
                }
            }
        }
    }.toList()

    sealed class Event {
        object CloseScreen : Event()
    }
}
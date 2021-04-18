package com.gyorgyzoltan.sprayApp.presentation.feature.main.work.configuration.nozzlePicker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.gyorgyzoltan.sprayApp.domain.nozzle.NozzleTypesUseCase
import com.gyorgyzoltan.sprayApp.domain.nozzle.NozzlesUseCase
import com.gyorgyzoltan.sprayApp.domain.nozzle.RefreshNozzleTypesUseCase
import com.gyorgyzoltan.sprayApp.domain.nozzle.RefreshNozzlesUseCase
import com.gyorgyzoltan.sprayApp.model.DataState
import com.gyorgyzoltan.sprayApp.model.nozzle.Nozzle
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleType
import com.gyorgyzoltan.sprayApp.presentation.R
import com.gyorgyzoltan.sprayApp.presentation.feature.main.work.configuration.nozzlePicker.list.NozzlePickerListItem
import com.gyorgyzoltan.sprayApp.presentation.feature.main.work.configuration.nozzlePicker.list.NozzleTypeViewHolder
import com.gyorgyzoltan.sprayApp.presentation.feature.main.work.configuration.nozzlePicker.list.NozzleViewHolder
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.ListViewModel
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.list.TextViewHolder
import com.gyorgyzoltan.sprayApp.presentation.utils.consume
import com.gyorgyzoltan.sprayApp.utils.Consumable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

internal class NozzlePickerViewModel(
    nozzles: NozzlesUseCase,
    private val nozzleTypes: NozzleTypesUseCase,
    private val refreshNozzleTypes: RefreshNozzleTypesUseCase,
    private val refreshNozzles: RefreshNozzlesUseCase
) : ListViewModel<NozzlePickerListItem>(true) {

    private val selectedNozzleType = MutableStateFlow<NozzleType?>(null)
    override val items = combine(
        selectedNozzleType,
        nozzleTypes(),
        nozzles()
    ) { selectedNozzleType, nozzleTypes, nozzles ->
        if (selectedNozzleType == null) {
            nozzleTypes.toNozzleTypePickerItems()
        } else {
            nozzles.toNozzlePickerItems()
        }
    }.asLiveData()
    override val isLoading = combine(
        selectedNozzleType,
        nozzleTypes(),
        nozzles()
    ) { selectedNozzleType, nozzleTypes, nozzles ->
        if (selectedNozzleType == null) {
            nozzleTypes is DataState.Loading
        } else {
            nozzleTypes is DataState.Loading || nozzles is DataState.Loading
        }
    }.asLiveData()
    private val _events = MutableLiveData<Consumable<Event>>()
    val events: LiveData<Consumable<Event>> = _events

    init {
        loadData(false)
    }

    override fun loadData(isForceRefresh: Boolean) {
        viewModelScope.launch {
            if (selectedNozzleType.value == null) {
                refreshNozzleTypes(isForceRefresh)
            } else {
                refreshNozzles(isForceRefresh)
            }
        }
    }

    fun onNozzleSelected(nozzle: Nozzle) {
        // TODO: Save selection
        _events.value = Consumable(Event.CloseScreen)
    }

    fun onNozzleTypeSelected(nozzleType: NozzleType) {
        selectedNozzleType.value = nozzleType
        loadData(false)
    }

    fun onBackPressed() = if (selectedNozzleType.value == null) {
        false
    } else consume {
        selectedNozzleType.value = null
    }

    private fun DataState<List<NozzleType>>.toNozzleTypePickerItems() = mutableListOf<NozzlePickerListItem>().apply {
        data.let { nozzleTypes ->
            when {
                nozzleTypes?.isNotEmpty() == true -> {
                    add(TextViewHolder.UiModel(R.string.nozzle_picker_hint_select_type))
                    addAll(nozzleTypes.map { NozzleTypeViewHolder.UiModel(it) })
                }
                nozzleTypes?.isEmpty() == true -> {
                    add(TextViewHolder.UiModel(R.string.nozzle_picker_no_nozzle_types_found))
                }
                nozzleTypes == null -> if (this is DataState.Error<*>) {
                    add(TextViewHolder.UiModel(R.string.general_error))
                }
            }
        }
    }.toList()

    private fun DataState<List<Nozzle>>.toNozzlePickerItems() = mutableListOf<NozzlePickerListItem>().apply {
        data?.filter { it.type == selectedNozzleType.value }.let { nozzles ->
            when {
                nozzles?.isNotEmpty() == true -> {
                    add(TextViewHolder.UiModel(R.string.nozzle_picker_hint_select_nozzle))
                    addAll(nozzles.map { NozzleViewHolder.UiModel(it, false) })
                }
                nozzles?.isEmpty() == true -> {
                    add(TextViewHolder.UiModel(R.string.nozzle_picker_no_nozzles_found))
                }
                nozzles == null -> if (this is DataState.Error<*>) {
                    add(TextViewHolder.UiModel(R.string.general_error))
                }
            }
        }
    }.toList()

    sealed class Event {
        object CloseScreen : Event()
    }
}
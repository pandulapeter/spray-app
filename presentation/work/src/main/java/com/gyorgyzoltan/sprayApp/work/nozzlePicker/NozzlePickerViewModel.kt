package com.gyorgyzoltan.sprayApp.work.nozzlePicker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.gyorgyzoltan.sprayApp.domain.nozzle.NozzlesUseCase
import com.gyorgyzoltan.sprayApp.domain.nozzle.RefreshNozzlesUseCase
import com.gyorgyzoltan.sprayApp.main.shared.list.ListViewModel
import com.gyorgyzoltan.sprayApp.main.shared.utilities.consume
import com.gyorgyzoltan.sprayApp.model.DataState
import com.gyorgyzoltan.sprayApp.model.nozzle.Nozzle
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleType
import com.gyorgyzoltan.sprayApp.main.shared.utilities.Consumable
import com.gyorgyzoltan.sprayApp.work.nozzlePicker.list.NozzlePickerEmptyStateViewHolder
import com.gyorgyzoltan.sprayApp.work.nozzlePicker.list.NozzlePickerListItem
import com.gyorgyzoltan.sprayApp.work.nozzlePicker.list.NozzlePickerNozzleDarkViewHolder
import com.gyorgyzoltan.sprayApp.work.nozzlePicker.list.NozzlePickerNozzleLightViewHolder
import com.gyorgyzoltan.sprayApp.work.nozzlePicker.list.NozzlePickerNozzleTypeViewHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

internal class NozzlePickerViewModel(
    nozzles: NozzlesUseCase,
    private val refreshNozzles: RefreshNozzlesUseCase
) : ListViewModel<NozzlePickerListItem>(true) {

    private val selectedNozzleType = MutableStateFlow<NozzleType?>(null)
    override val items = combine(selectedNozzleType, nozzles()) { selectedNozzleType, nozzles ->
        nozzles.toItems(selectedNozzleType)
    }.flowOn(Dispatchers.Default).asLiveData()
    override val shouldShowLoadingIndicator = nozzles().map { it is DataState.Loading }.asLiveData()
    override val shouldShowErrorView = nozzles().map { it is DataState.Error && it.data == null }.asLiveData()
    private val _events = MutableLiveData<Consumable<Event>>()
    val events: LiveData<Consumable<Event>> = _events

    init {
        nozzles().onEach {
            if (it is DataState.Error && it.data != null) {
                _events.value = Consumable(Event.ShowErrorSnackbar)
            }
        }.launchIn(viewModelScope)
        loadData(false)
    }

    override fun loadData(isForceRefresh: Boolean) {
        viewModelScope.launch {
            refreshNozzles(isForceRefresh)
        }
    }

    fun onNozzleSelected(nozzle: Nozzle) {
        // TODO: Save selection
        _events.value = Consumable(Event.CloseScreen)
    }

    fun onNozzleTypeSelected(nozzleType: NozzleType) {
        selectedNozzleType.value = if (selectedNozzleType.value == nozzleType) {
            null
        } else {
            nozzleType
        }
    }

    fun onBackPressed() = if (selectedNozzleType.value == null) {
        false
    } else consume {
        selectedNozzleType.value = null
    }

    private fun DataState<List<Nozzle>>.toItems(selectedNozzleType: NozzleType?) = mutableListOf<NozzlePickerListItem>().apply {
        data?.let { nozzles ->
            if (nozzles.isEmpty()) {
                add(NozzlePickerEmptyStateViewHolder.UiModel())
            } else {
                nozzles
                    .map { it.type }
                    .distinctBy { it.name }
                    .forEach { nozzleType ->
                        val isExpanded = nozzleType.name == selectedNozzleType?.name
                        add(
                            NozzlePickerNozzleTypeViewHolder.UiModel(
                                nozzleType = nozzleType,
                                isExpanded = isExpanded
                            )
                        )
                        if (isExpanded) {
                            addAll(
                                nozzles
                                    .filter { it.type.name == nozzleType.name }
                                    .map { nozzle ->
                                        if (nozzle.color.isDark) {
                                            NozzlePickerNozzleDarkViewHolder.UiModel(nozzle)
                                        } else {
                                            NozzlePickerNozzleLightViewHolder.UiModel(nozzle)
                                        }
                                    }
                            )
                        }
                    }
            }
        }
    }.toList()

    sealed class Event {
        object ShowErrorSnackbar : Event()
        object CloseScreen : Event()
    }
}
package com.gyorgyzoltan.sprayApp.work.configuration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.gyorgyzoltan.sprayApp.domain.configuration.ConfigurationUseCase
import com.gyorgyzoltan.sprayApp.domain.configuration.RefreshConfigurationUseCase
import com.gyorgyzoltan.sprayApp.main.R
import com.gyorgyzoltan.sprayApp.main.shared.list.ListViewModel
import com.gyorgyzoltan.sprayApp.main.shared.utilities.Consumable
import com.gyorgyzoltan.sprayApp.model.Configuration
import com.gyorgyzoltan.sprayApp.model.DataState
import com.gyorgyzoltan.sprayApp.work.configuration.list.ConfigurationDoneButtonViewHolder
import com.gyorgyzoltan.sprayApp.work.configuration.list.ConfigurationListItem
import com.gyorgyzoltan.sprayApp.work.configuration.list.ConfigurationSelectedNozzleViewHolder
import com.gyorgyzoltan.sprayApp.work.configuration.list.ConfigurationTextViewHolder
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

internal class ConfigurationViewModel(
    configuration: ConfigurationUseCase,
    private val refreshConfiguration: RefreshConfigurationUseCase
) : ListViewModel<ConfigurationListItem>(true) {

    override val items = configuration().map { it.toItems() }.asLiveData()
    override val shouldShowLoadingIndicator = configuration().map { it is DataState.Loading }.asLiveData()
    override val shouldShowErrorView = configuration().map { it is DataState.Error && it.data == null }.asLiveData()
    private val _events = MutableLiveData<Consumable<Event>>()
    val events: LiveData<Consumable<Event>> = _events

    init {
        configuration().onEach {
            if (it is DataState.Error && it.data != null) {
                _events.value = Consumable(Event.ShowErrorSnackbar)
            }
        }.launchIn(viewModelScope)
        loadData(false)
    }

    override fun loadData(isForceRefresh: Boolean) {
        viewModelScope.launch { refreshConfiguration(isForceRefresh) }
    }

    fun onDoneButtonClicked() {
        _events.value = Consumable(Event.CloseScreen)
    }

    fun onNozzleClicked() {
        _events.value = Consumable(Event.NavigateToNozzlePicker)
    }

    private fun DataState<Configuration>.toItems() = mutableListOf<ConfigurationListItem>().apply {
        data?.let { configuration ->
            add(ConfigurationTextViewHolder.UiModel(R.string.configuration_selected_nozzle))
            add(ConfigurationSelectedNozzleViewHolder.UiModel(configuration.nozzle))
            add(ConfigurationTextViewHolder.UiModel(R.string.configuration_wheel_radius))
            add(ConfigurationTextViewHolder.UiModel(R.string.configuration_screw_count))
            add(ConfigurationTextViewHolder.UiModel(R.string.configuration_nozzle_count))
            add(ConfigurationTextViewHolder.UiModel(R.string.configuration_nozzle_distance))
            add(ConfigurationDoneButtonViewHolder.UiModel(configuration.isValid))
        }
    }.toList()

    sealed class Event {
        object NavigateToNozzlePicker : Event()
        object CloseScreen : Event()
        object ShowErrorSnackbar : Event()
    }
}
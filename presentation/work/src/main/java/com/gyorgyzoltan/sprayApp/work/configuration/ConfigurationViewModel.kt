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
import com.gyorgyzoltan.sprayApp.work.configuration.list.ConfigurationNoSelectionViewHolder
import com.gyorgyzoltan.sprayApp.work.configuration.list.ConfigurationSelectedNozzleDarkViewHolder
import com.gyorgyzoltan.sprayApp.work.configuration.list.ConfigurationSelectedNozzleLightViewHolder
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

    fun onNoSelectionItemClicked(hintResourceId: Int) {
        _events.value = when (hintResourceId) {
            R.string.configuration_no_nozzle_selected -> Consumable(Event.NavigateToNozzlePicker)
            R.string.configuration_no_wheel_radius_set -> Consumable(Event.NavigateToWheelRadiusPicker)
            R.string.configuration_no_screw_count_set -> Consumable(Event.NavigateToScrewCountPicker)
            R.string.configuration_no_nozzle_count_set -> Consumable(Event.NavigateToNozzleCountPicker)
            R.string.configuration_no_nozzle_distance_set -> Consumable(Event.NavigateToNozzleDistancePicker)
            else -> throw  IllegalArgumentException("Invalid hint resource ID: $hintResourceId")
        }
    }

    fun onNozzleClicked() {
        _events.value = Consumable(Event.NavigateToNozzlePicker)
    }

    private fun DataState<Configuration>.toItems() = mutableListOf<ConfigurationListItem>().apply {
        data?.let { configuration ->
            addNozzleSection(configuration)
            addWheelRadiusSection(configuration)
            addScrewCountSection(configuration)
            addNozzleCountSection(configuration)
            addNozzleDistanceSection(configuration)
            add(ConfigurationDoneButtonViewHolder.UiModel(configuration.isValid))
        }
    }.toList()

    private fun MutableList<ConfigurationListItem>.addNozzleSection(configuration: Configuration) {
        add(ConfigurationTextViewHolder.UiModel(R.string.configuration_selected_nozzle))
        add(
            configuration.nozzle.let { selectedNozzle ->
                when {
                    selectedNozzle == null -> ConfigurationNoSelectionViewHolder.UiModel(R.string.configuration_no_nozzle_selected)
                    selectedNozzle.color.isDark -> ConfigurationSelectedNozzleDarkViewHolder.UiModel(configuration.nozzle)
                    else -> ConfigurationSelectedNozzleLightViewHolder.UiModel(configuration.nozzle)
                }
            }
        )
    }

    private fun MutableList<ConfigurationListItem>.addWheelRadiusSection(configuration: Configuration) {
        add(ConfigurationTextViewHolder.UiModel(R.string.configuration_wheel_radius))
        add(
            if (configuration.wheelRadius == null) {
                ConfigurationNoSelectionViewHolder.UiModel(R.string.configuration_no_wheel_radius_set)
            } else {
                TODO()
            }
        )
    }

    private fun MutableList<ConfigurationListItem>.addScrewCountSection(configuration: Configuration) {
        add(ConfigurationTextViewHolder.UiModel(R.string.configuration_screw_count))
        add(
            if (configuration.screwCount == null) {
                ConfigurationNoSelectionViewHolder.UiModel(R.string.configuration_no_screw_count_set)
            } else {
                TODO()
            }
        )
    }

    private fun MutableList<ConfigurationListItem>.addNozzleCountSection(configuration: Configuration) {
        add(ConfigurationTextViewHolder.UiModel(R.string.configuration_nozzle_count))
        add(
            if (configuration.nozzleCount == null) {
                ConfigurationNoSelectionViewHolder.UiModel(R.string.configuration_no_nozzle_count_set)
            } else {
                TODO()
            }
        )
    }

    private fun MutableList<ConfigurationListItem>.addNozzleDistanceSection(configuration: Configuration) {
        add(ConfigurationTextViewHolder.UiModel(R.string.configuration_nozzle_distance))
        add(
            if (configuration.nozzleDistance == null) {
                ConfigurationNoSelectionViewHolder.UiModel(R.string.configuration_no_nozzle_distance_set)
            } else {
                TODO()
            }
        )
    }

    sealed class Event {
        object NavigateToNozzlePicker : Event()
        object NavigateToWheelRadiusPicker : Event()
        object NavigateToScrewCountPicker : Event()
        object NavigateToNozzleCountPicker : Event()
        object NavigateToNozzleDistancePicker : Event()
        object CloseScreen : Event()
        object ShowErrorSnackbar : Event()
    }
}
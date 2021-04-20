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
import com.gyorgyzoltan.sprayApp.work.configuration.list.ConfigurationSelectedNozzleCountViewHolder
import com.gyorgyzoltan.sprayApp.work.configuration.list.ConfigurationSelectedNozzleDarkViewHolder
import com.gyorgyzoltan.sprayApp.work.configuration.list.ConfigurationSelectedNozzleDistanceViewHolder
import com.gyorgyzoltan.sprayApp.work.configuration.list.ConfigurationSelectedNozzleLightViewHolder
import com.gyorgyzoltan.sprayApp.work.configuration.list.ConfigurationSelectedScrewCountViewHolder
import com.gyorgyzoltan.sprayApp.work.configuration.list.ConfigurationSelectedWheelRadiusViewHolder
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
    private var currentWheelRadius: Float? = null
    private var currentScrewCount: Int? = null
    private var currentNozzleCount: Int? = null
    private var currentNozzleDistance: Float? = null

    init {
        configuration().onEach {
            if (it is DataState.Error && it.data != null) {
                _events.value = Consumable(Event.ShowErrorSnackbar)
            }
            currentWheelRadius = it.data?.wheelRadius
            currentScrewCount = it.data?.screwCount
            currentNozzleCount = it.data?.nozzleCount
            currentNozzleDistance = it.data?.nozzleDistance
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
            R.string.configuration_no_wheel_radius_set -> Consumable(Event.NavigateToWheelRadiusPicker(currentWheelRadius))
            R.string.configuration_no_screw_count_set -> Consumable(Event.NavigateToScrewCountPicker(currentScrewCount))
            R.string.configuration_no_nozzle_count_set -> Consumable(Event.NavigateToNozzleCountPicker(currentNozzleCount))
            R.string.configuration_no_nozzle_distance_set -> Consumable(Event.NavigateToNozzleDistancePicker(currentNozzleDistance))
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
        add(ConfigurationTextViewHolder.UiModel(R.string.configuration_nozzle))
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
            configuration.wheelRadius.let { selectedWheelRadius ->
                if (selectedWheelRadius == null) {
                    ConfigurationNoSelectionViewHolder.UiModel(R.string.configuration_no_wheel_radius_set)
                } else {
                    ConfigurationSelectedWheelRadiusViewHolder.UiModel(selectedWheelRadius)
                }
            }
        )
    }

    private fun MutableList<ConfigurationListItem>.addScrewCountSection(configuration: Configuration) {
        add(ConfigurationTextViewHolder.UiModel(R.string.configuration_screw_count))
        add(
            configuration.screwCount.let { selectedScrewCount ->
                if (selectedScrewCount == null) {
                    ConfigurationNoSelectionViewHolder.UiModel(R.string.configuration_no_screw_count_set)
                } else {
                    ConfigurationSelectedScrewCountViewHolder.UiModel(selectedScrewCount)
                }
            }
        )
    }

    private fun MutableList<ConfigurationListItem>.addNozzleCountSection(configuration: Configuration) {
        add(ConfigurationTextViewHolder.UiModel(R.string.configuration_nozzle_count))
        add(
            configuration.nozzleCount.let { selectedNozzleCount ->
                if (selectedNozzleCount == null) {
                    ConfigurationNoSelectionViewHolder.UiModel(R.string.configuration_no_nozzle_count_set)
                } else {
                    ConfigurationSelectedNozzleCountViewHolder.UiModel(selectedNozzleCount)
                }
            }
        )
    }

    private fun MutableList<ConfigurationListItem>.addNozzleDistanceSection(configuration: Configuration) {
        add(ConfigurationTextViewHolder.UiModel(R.string.configuration_nozzle_distance))
        add(
            configuration.nozzleDistance.let { selectedNozzleDistance ->
                if (selectedNozzleDistance == null) {
                    ConfigurationNoSelectionViewHolder.UiModel(R.string.configuration_no_nozzle_distance_set)
                } else {
                    ConfigurationSelectedNozzleDistanceViewHolder.UiModel(selectedNozzleDistance)
                }
            }
        )
    }

    sealed class Event {
        object NavigateToNozzlePicker : Event()
        data class NavigateToWheelRadiusPicker(val currentWheelRadius: Float?) : Event()
        data class NavigateToScrewCountPicker(val currentScrewCount: Int?) : Event()
        data class NavigateToNozzleCountPicker(val currentNozzleCount: Int?) : Event()
        data class NavigateToNozzleDistancePicker(val currentNozzleDistance: Float?) : Event()
        object CloseScreen : Event()
        object ShowErrorSnackbar : Event()
    }
}
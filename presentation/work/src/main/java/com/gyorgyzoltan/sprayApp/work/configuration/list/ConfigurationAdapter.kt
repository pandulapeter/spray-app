package com.gyorgyzoltan.sprayApp.work.configuration.list

import android.view.ViewGroup
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseAdapter
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.work.R
import kotlinx.coroutines.CoroutineScope

internal class ConfigurationAdapter(
    scope: CoroutineScope,
    private val onDoneButtonClicked: () -> Unit,
    private val onNoSelectionItemClicked: (Int) -> Unit,
    private val onNozzleClicked: () -> Unit
) : BaseAdapter<ConfigurationListItem>(scope) {

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is ConfigurationTextViewHolder.UiModel -> R.layout.item_configuration_text
        is ConfigurationNoSelectionViewHolder.UiModel -> R.layout.item_configuration_no_selection
        is ConfigurationSelectedNozzleDarkViewHolder.UiModel -> R.layout.item_configuration_selected_nozzle_dark
        is ConfigurationSelectedNozzleLightViewHolder.UiModel -> R.layout.item_configuration_selected_nozzle_light
        is ConfigurationSelectedWheelRadiusViewHolder.UiModel -> R.layout.item_configuration_selected_wheel_radius
        is ConfigurationSelectedScrewCountViewHolder.UiModel -> R.layout.item_configuration_selected_screw_count
        is ConfigurationSelectedNozzleCountViewHolder.UiModel -> R.layout.item_configuration_selected_nozzle_count
        is ConfigurationSelectedNozzleDistanceViewHolder.UiModel -> R.layout.item_configuration_selected_nozzle_distance
        is ConfigurationDoneButtonViewHolder.UiModel -> R.layout.item_configuration_done_button
        else -> super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*, *> = when (viewType) {
        R.layout.item_configuration_text -> ConfigurationTextViewHolder.create(parent)
        R.layout.item_configuration_no_selection -> ConfigurationNoSelectionViewHolder.create(parent, onNoSelectionItemClicked)
        R.layout.item_configuration_selected_nozzle_dark -> ConfigurationSelectedNozzleDarkViewHolder.create(parent, onNozzleClicked)
        R.layout.item_configuration_selected_nozzle_light -> ConfigurationSelectedNozzleLightViewHolder.create(parent, onNozzleClicked)
        R.layout.item_configuration_selected_wheel_radius -> ConfigurationSelectedWheelRadiusViewHolder.create(parent, onNoSelectionItemClicked)
        R.layout.item_configuration_selected_screw_count -> ConfigurationSelectedScrewCountViewHolder.create(parent, onNoSelectionItemClicked)
        R.layout.item_configuration_selected_nozzle_count -> ConfigurationSelectedNozzleCountViewHolder.create(parent, onNoSelectionItemClicked)
        R.layout.item_configuration_selected_nozzle_distance -> ConfigurationSelectedNozzleDistanceViewHolder.create(parent, onNoSelectionItemClicked)
        R.layout.item_configuration_done_button -> ConfigurationDoneButtonViewHolder.create(parent, onDoneButtonClicked)
        else -> super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*, *>, position: Int) = when (holder) {
        is ConfigurationTextViewHolder -> holder.bind(getItem(position) as ConfigurationTextViewHolder.UiModel)
        is ConfigurationNoSelectionViewHolder -> holder.bind(getItem(position) as ConfigurationNoSelectionViewHolder.UiModel)
        is ConfigurationSelectedNozzleDarkViewHolder -> holder.bind(getItem(position) as ConfigurationSelectedNozzleDarkViewHolder.UiModel)
        is ConfigurationSelectedNozzleLightViewHolder -> holder.bind(getItem(position) as ConfigurationSelectedNozzleLightViewHolder.UiModel)
        is ConfigurationSelectedWheelRadiusViewHolder -> holder.bind(getItem(position) as ConfigurationSelectedWheelRadiusViewHolder.UiModel)
        is ConfigurationSelectedScrewCountViewHolder -> holder.bind(getItem(position) as ConfigurationSelectedScrewCountViewHolder.UiModel)
        is ConfigurationSelectedNozzleCountViewHolder -> holder.bind(getItem(position) as ConfigurationSelectedNozzleCountViewHolder.UiModel)
        is ConfigurationSelectedNozzleDistanceViewHolder -> holder.bind(getItem(position) as ConfigurationSelectedNozzleDistanceViewHolder.UiModel)
        is ConfigurationDoneButtonViewHolder -> holder.bind(getItem(position) as ConfigurationDoneButtonViewHolder.UiModel)
        else -> super.onBindViewHolder(holder, position)
    }
}
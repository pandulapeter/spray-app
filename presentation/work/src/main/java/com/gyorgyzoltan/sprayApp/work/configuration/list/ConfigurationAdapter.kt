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
        is ConfigurationDoneButtonViewHolder.UiModel -> R.layout.item_configuration_done_button
        is ConfigurationNoSelectionViewHolder.UiModel -> R.layout.item_configuration_no_selection
        is ConfigurationSelectedNozzleDarkViewHolder.UiModel -> R.layout.item_configuration_selected_nozzle_dark
        is ConfigurationSelectedNozzleLightViewHolder.UiModel -> R.layout.item_configuration_selected_nozzle_light
        is ConfigurationTextViewHolder.UiModel -> R.layout.item_configuration_text
        else -> super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*, *> = when (viewType) {
        R.layout.item_configuration_done_button -> ConfigurationDoneButtonViewHolder.create(parent, onDoneButtonClicked)
        R.layout.item_configuration_no_selection -> ConfigurationNoSelectionViewHolder.create(parent, onNoSelectionItemClicked)
        R.layout.item_configuration_selected_nozzle_dark -> ConfigurationSelectedNozzleDarkViewHolder.create(parent, onNozzleClicked)
        R.layout.item_configuration_selected_nozzle_light -> ConfigurationSelectedNozzleLightViewHolder.create(parent, onNozzleClicked)
        R.layout.item_configuration_text -> ConfigurationTextViewHolder.create(parent)
        else -> super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*, *>, position: Int) = when (holder) {
        is ConfigurationDoneButtonViewHolder -> holder.bind(getItem(position) as ConfigurationDoneButtonViewHolder.UiModel)
        is ConfigurationNoSelectionViewHolder -> holder.bind(getItem(position) as ConfigurationNoSelectionViewHolder.UiModel)
        is ConfigurationSelectedNozzleDarkViewHolder -> holder.bind(getItem(position) as ConfigurationSelectedNozzleDarkViewHolder.UiModel)
        is ConfigurationSelectedNozzleLightViewHolder -> holder.bind(getItem(position) as ConfigurationSelectedNozzleLightViewHolder.UiModel)
        is ConfigurationTextViewHolder -> holder.bind(getItem(position) as ConfigurationTextViewHolder.UiModel)
        else -> super.onBindViewHolder(holder, position)
    }
}
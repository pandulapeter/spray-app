package com.gyorgyzoltan.sprayApp.feature.main.work.configuration.list

import android.view.ViewGroup
import com.gyorgyzoltan.sprayApp.R
import com.gyorgyzoltan.sprayApp.feature.shared.list.BaseAdapter
import com.gyorgyzoltan.sprayApp.feature.shared.list.BaseViewHolder
import kotlinx.coroutines.CoroutineScope

class ConfigurationAdapter(
    scope: CoroutineScope,
    private val onNozzleClicked: () -> Unit,
    private val onDoneButtonClicked: () -> Unit
) : BaseAdapter<ConfigurationListItem>(scope) {

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is DoneButtonViewHolder.UiModel -> R.layout.item_configuration_done_button
        is SelectedNozzleViewHolder.UiModel -> R.layout.item_configuration_selected_nozzle
        else -> super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*, *> = when (viewType) {
        R.layout.item_configuration_done_button -> DoneButtonViewHolder.create(parent, onDoneButtonClicked)
        R.layout.item_configuration_selected_nozzle -> SelectedNozzleViewHolder.create(parent, onNozzleClicked)
        else -> super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*, *>, position: Int) = when (holder) {
        is DoneButtonViewHolder -> holder.bind(getItem(position) as DoneButtonViewHolder.UiModel)
        is SelectedNozzleViewHolder -> holder.bind(getItem(position) as SelectedNozzleViewHolder.UiModel)
        else -> super.onBindViewHolder(holder, position)
    }
}
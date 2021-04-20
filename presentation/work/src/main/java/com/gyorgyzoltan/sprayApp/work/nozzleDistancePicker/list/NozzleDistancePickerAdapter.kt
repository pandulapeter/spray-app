package com.gyorgyzoltan.sprayApp.work.nozzleDistancePicker.list

import android.view.ViewGroup
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseAdapter
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.work.R
import kotlinx.coroutines.CoroutineScope

internal class NozzleDistancePickerAdapter(
    scope: CoroutineScope,
    private val onDoneButtonPressed: () -> Unit
) : BaseAdapter<NozzleDistancePickerListItem>(scope) {

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is NozzleDistanceHintViewHolder.UiModel -> R.layout.item_nozzle_distance_hint
        is NozzleDistanceDoneButtonViewHolder.UiModel -> R.layout.item_nozzle_distance_done_button
        else -> super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*, *> = when (viewType) {
        R.layout.item_nozzle_distance_hint -> NozzleDistanceHintViewHolder.create(parent)
        R.layout.item_nozzle_distance_done_button -> NozzleDistanceDoneButtonViewHolder.create(parent, onDoneButtonPressed)
        else -> super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*, *>, position: Int) = when (holder) {
        is NozzleDistanceHintViewHolder -> holder.bind(getItem(position) as NozzleDistanceHintViewHolder.UiModel)
        is NozzleDistanceDoneButtonViewHolder -> holder.bind(getItem(position) as NozzleDistanceDoneButtonViewHolder.UiModel)
        else -> super.onBindViewHolder(holder, position)
    }
}
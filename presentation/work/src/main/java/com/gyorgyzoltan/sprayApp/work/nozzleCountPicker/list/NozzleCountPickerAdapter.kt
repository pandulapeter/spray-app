package com.gyorgyzoltan.sprayApp.work.nozzleCountPicker.list

import android.view.ViewGroup
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseAdapter
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.work.R
import kotlinx.coroutines.CoroutineScope

internal class NozzleCountPickerAdapter(
    scope: CoroutineScope,
    private val onNozzleCountChanged: (Int) -> Unit,
    private val onDoneButtonPressed: () -> Unit
) : BaseAdapter<NozzleCountPickerListItem>(scope) {

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is NozzleCountHintViewHolder.UiModel -> R.layout.item_nozzle_count_hint
        is NozzleCountInputViewHolder.UiModel -> R.layout.item_nozzle_count_input
        is NozzleCountDoneButtonViewHolder.UiModel -> R.layout.item_nozzle_count_done_button
        else -> super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*, *> = when (viewType) {
        R.layout.item_nozzle_count_hint -> NozzleCountHintViewHolder.create(parent)
        R.layout.item_nozzle_count_input -> NozzleCountInputViewHolder.create(parent, onNozzleCountChanged)
        R.layout.item_nozzle_count_done_button -> NozzleCountDoneButtonViewHolder.create(parent, onDoneButtonPressed)
        else -> super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*, *>, position: Int) = when (holder) {
        is NozzleCountHintViewHolder -> holder.bind(getItem(position) as NozzleCountHintViewHolder.UiModel)
        is NozzleCountInputViewHolder -> holder.bind(getItem(position) as NozzleCountInputViewHolder.UiModel)
        is NozzleCountDoneButtonViewHolder -> holder.bind(getItem(position) as NozzleCountDoneButtonViewHolder.UiModel)
        else -> super.onBindViewHolder(holder, position)
    }
}
package com.gyorgyzoltan.sprayApp.work.screwCountPicker.list

import android.view.ViewGroup
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseAdapter
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.work.R
import kotlinx.coroutines.CoroutineScope

internal class ScrewCountPickerAdapter(
    scope: CoroutineScope,
    private val onDoneButtonPressed: () -> Unit
) : BaseAdapter<ScrewCountPickerListItem>(scope) {

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is ScrewCountHintViewHolder.UiModel -> R.layout.item_screw_count_hint
        is ScrewCountDoneButtonViewHolder.UiModel -> R.layout.item_screw_count_done_button
        else -> super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*, *> = when (viewType) {
        R.layout.item_screw_count_hint -> ScrewCountHintViewHolder.create(parent)
        R.layout.item_screw_count_done_button -> ScrewCountDoneButtonViewHolder.create(parent, onDoneButtonPressed)
        else -> super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*, *>, position: Int) = when (holder) {
        is ScrewCountHintViewHolder -> holder.bind(getItem(position) as ScrewCountHintViewHolder.UiModel)
        is ScrewCountDoneButtonViewHolder -> holder.bind(getItem(position) as ScrewCountDoneButtonViewHolder.UiModel)
        else -> super.onBindViewHolder(holder, position)
    }
}
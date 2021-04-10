package com.gyorgyzoltan.sprayApp.presentation.feature.main.work.configuration.nozzlePicker.list

import android.view.ViewGroup
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.model.nozzle.Nozzle
import com.gyorgyzoltan.sprayApp.presentation.R
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.list.BaseAdapter
import kotlinx.coroutines.CoroutineScope

internal class NozzlePickerAdapter(
    scope: CoroutineScope,
    private val onNozzleSelected: (Nozzle) -> Unit
) : BaseAdapter<NozzlePickerListItem>(scope) {

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is NozzleViewHolder.UiModel -> R.layout.item_nozzle_picker_nozzle
        else -> super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*, *> = when (viewType) {
        R.layout.item_nozzle_picker_nozzle -> NozzleViewHolder.create(parent, onNozzleSelected)
        else -> super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*, *>, position: Int) = when (holder) {
        is NozzleViewHolder -> holder.bind(getItem(position) as NozzleViewHolder.UiModel)
        else -> super.onBindViewHolder(holder, position)
    }
}
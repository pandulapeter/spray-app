package com.gyorgyzoltan.sprayApp.presentation.feature.main.work.configuration.nozzlePicker.list

import android.view.ViewGroup
import com.gyorgyzoltan.sprayApp.model.nozzle.Nozzle
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleType
import com.gyorgyzoltan.sprayApp.presentation.R
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.list.BaseAdapter
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.list.BaseViewHolder
import kotlinx.coroutines.CoroutineScope

internal class NozzlePickerAdapter(
    scope: CoroutineScope,
    private val onNozzleSelected: (Nozzle) -> Unit,
    private val onNozzleTypeSelected: (NozzleType) -> Unit
) : BaseAdapter<NozzlePickerListItem>(scope) {

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is NozzleTypeViewHolder.UiModel -> R.layout.item_nozzle_picker_nozzle_type
        is NozzleDarkViewHolder.UiModel -> R.layout.item_nozzle_picker_nozzle_dark
        is NozzleLightViewHolder.UiModel -> R.layout.item_nozzle_picker_nozzle_light
        else -> super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*, *> = when (viewType) {
        R.layout.item_nozzle_picker_nozzle_type -> NozzleTypeViewHolder.create(parent, onNozzleTypeSelected)
        R.layout.item_nozzle_picker_nozzle_dark -> NozzleDarkViewHolder.create(parent, onNozzleSelected)
        R.layout.item_nozzle_picker_nozzle_light -> NozzleLightViewHolder.create(parent, onNozzleSelected)
        else -> super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*, *>, position: Int) = when (holder) {
        is NozzleTypeViewHolder -> holder.bind(getItem(position) as NozzleTypeViewHolder.UiModel)
        is NozzleDarkViewHolder -> holder.bind(getItem(position) as NozzleDarkViewHolder.UiModel)
        is NozzleLightViewHolder -> holder.bind(getItem(position) as NozzleLightViewHolder.UiModel)
        else -> super.onBindViewHolder(holder, position)
    }
}
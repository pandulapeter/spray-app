package com.gyorgyzoltan.sprayApp.work.nozzlePicker.list

import android.view.ViewGroup
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseAdapter
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.model.nozzle.Nozzle
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleType
import com.gyorgyzoltan.sprayApp.work.R
import kotlinx.coroutines.CoroutineScope

internal class NozzlePickerAdapter(
    scope: CoroutineScope,
    private val onNozzleSelected: (Nozzle) -> Unit,
    private val onNozzleTypeSelected: (NozzleType) -> Unit
) : BaseAdapter<NozzlePickerListItem>(scope) {

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is NozzlePickerNozzleTypeViewHolder.UiModel -> R.layout.item_nozzle_picker_nozzle_type
        is NozzlePickerNozzleDarkViewHolder.UiModel -> R.layout.item_nozzle_picker_nozzle_dark
        is NozzlePickerNozzleLightViewHolder.UiModel -> R.layout.item_nozzle_picker_nozzle_light
        else -> super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*, *> = when (viewType) {
        R.layout.item_nozzle_picker_nozzle_type -> NozzlePickerNozzleTypeViewHolder.create(parent, onNozzleTypeSelected)
        R.layout.item_nozzle_picker_nozzle_dark -> NozzlePickerNozzleDarkViewHolder.create(parent, onNozzleSelected)
        R.layout.item_nozzle_picker_nozzle_light -> NozzlePickerNozzleLightViewHolder.create(parent, onNozzleSelected)
        else -> super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*, *>, position: Int) = when (holder) {
        is NozzlePickerNozzleTypeViewHolder -> holder.bind(getItem(position) as NozzlePickerNozzleTypeViewHolder.UiModel)
        is NozzlePickerNozzleDarkViewHolder -> holder.bind(getItem(position) as NozzlePickerNozzleDarkViewHolder.UiModel)
        is NozzlePickerNozzleLightViewHolder -> holder.bind(getItem(position) as NozzlePickerNozzleLightViewHolder.UiModel)
        else -> super.onBindViewHolder(holder, position)
    }
}
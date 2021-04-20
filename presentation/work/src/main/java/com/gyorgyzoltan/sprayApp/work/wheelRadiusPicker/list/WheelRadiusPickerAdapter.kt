package com.gyorgyzoltan.sprayApp.work.wheelRadiusPicker.list

import android.view.ViewGroup
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseAdapter
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.work.R
import kotlinx.coroutines.CoroutineScope

internal class WheelRadiusPickerAdapter(
    scope: CoroutineScope,
    private val onWheelRadiusChanged: (Float) -> Unit,
    private val onDoneButtonPressed: () -> Unit
) : BaseAdapter<WheelRadiusPickerListItem>(scope) {

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is WheelRadiusHintViewHolder.UiModel -> R.layout.item_wheel_radius_hint
        is WheelRadiusInputViewHolder.UiModel -> R.layout.item_wheel_radius_input
        is WheelRadiusDoneButtonViewHolder.UiModel -> R.layout.item_wheel_radius_done_button
        else -> super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*, *> = when (viewType) {
        R.layout.item_wheel_radius_hint -> WheelRadiusHintViewHolder.create(parent)
        R.layout.item_wheel_radius_input -> WheelRadiusInputViewHolder.create(parent, onWheelRadiusChanged)
        R.layout.item_wheel_radius_done_button -> WheelRadiusDoneButtonViewHolder.create(parent, onDoneButtonPressed)
        else -> super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*, *>, position: Int) = when (holder) {
        is WheelRadiusHintViewHolder -> holder.bind(getItem(position) as WheelRadiusHintViewHolder.UiModel)
        is WheelRadiusInputViewHolder -> holder.bind(getItem(position) as WheelRadiusInputViewHolder.UiModel)
        is WheelRadiusDoneButtonViewHolder -> holder.bind(getItem(position) as WheelRadiusDoneButtonViewHolder.UiModel)
        else -> super.onBindViewHolder(holder, position)
    }
}
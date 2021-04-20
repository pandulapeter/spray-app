package com.gyorgyzoltan.sprayApp.work.wheelRadiusPicker.list

import android.view.ViewGroup
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseAdapter
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import kotlinx.coroutines.CoroutineScope

internal class WheelRadiusPickerAdapter(
    scope: CoroutineScope
) : BaseAdapter<WheelRadiusPickerListItem>(scope) {

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        else -> super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*, *> = when (viewType) {
        else -> super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*, *>, position: Int) = when (holder) {
        else -> super.onBindViewHolder(holder, position)
    }
}
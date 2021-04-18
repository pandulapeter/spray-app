package com.gyorgyzoltan.sprayApp.work.overview.list

import android.view.ViewGroup
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseAdapter
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.work.R
import kotlinx.coroutines.CoroutineScope

internal class WorkAdapter(
    scope: CoroutineScope
) : BaseAdapter<WorkListItem>(scope) {

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is WorkTextViewHolder.UiModel -> R.layout.item_work_text
        else -> super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*, *> = when (viewType) {
        R.layout.item_work_text -> WorkTextViewHolder.create(parent)
        else -> super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*, *>, position: Int) = when (holder) {
        is WorkTextViewHolder -> holder.bind(getItem(position) as WorkTextViewHolder.UiModel)
        else -> super.onBindViewHolder(holder, position)
    }
}
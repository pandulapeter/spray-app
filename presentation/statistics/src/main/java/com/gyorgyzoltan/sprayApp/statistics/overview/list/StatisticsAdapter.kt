package com.gyorgyzoltan.sprayApp.statistics.overview.list

import android.view.ViewGroup
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseAdapter
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.statistics.R
import kotlinx.coroutines.CoroutineScope

internal class StatisticsAdapter(
    scope: CoroutineScope
) : BaseAdapter<StatisticsListItem>(scope) {

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is StatisticsTextViewHolder.UiModel -> R.layout.item_statistics_text
        else -> super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*, *> = when (viewType) {
        R.layout.item_statistics_text -> StatisticsTextViewHolder.create(parent)
        else -> super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*, *>, position: Int) = when (holder) {
        is StatisticsTextViewHolder -> holder.bind(getItem(position) as StatisticsTextViewHolder.UiModel)
        else -> super.onBindViewHolder(holder, position)
    }
}
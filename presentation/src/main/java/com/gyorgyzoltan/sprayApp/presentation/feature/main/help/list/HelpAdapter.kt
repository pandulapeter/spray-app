package com.gyorgyzoltan.sprayApp.presentation.feature.main.help.list

import android.view.ViewGroup
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.presentation.R
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.list.BaseAdapter
import kotlinx.coroutines.CoroutineScope

internal class HelpAdapter(
    scope: CoroutineScope,
    private val onItemClicked: (ClickableItemViewHolder.UiModel) -> Unit
) : BaseAdapter<HelpListItem>(scope) {

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is ClickableItemViewHolder.UiModel -> R.layout.item_help_clickable_item
        else -> super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*, *> = when (viewType) {
        R.layout.item_help_clickable_item -> ClickableItemViewHolder.create(parent, onItemClicked)
        else -> super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*, *>, position: Int) = when (holder) {
        is ClickableItemViewHolder -> holder.bind(getItem(position) as ClickableItemViewHolder.UiModel)
        else -> super.onBindViewHolder(holder, position)
    }
}
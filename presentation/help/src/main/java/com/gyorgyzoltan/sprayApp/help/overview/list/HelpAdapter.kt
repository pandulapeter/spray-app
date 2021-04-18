package com.gyorgyzoltan.sprayApp.help.overview.list

import android.view.ViewGroup
import com.gyorgyzoltan.sprayApp.help.R
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseAdapter
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import kotlinx.coroutines.CoroutineScope

internal class HelpAdapter(
    scope: CoroutineScope,
    private val onItemClicked: (HelpClickableItemViewHolder.UiModel) -> Unit
) : BaseAdapter<HelpListItem>(scope) {

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is HelpClickableItemViewHolder.UiModel -> R.layout.item_help_clickable_item
        else -> super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*, *> = when (viewType) {
        R.layout.item_help_clickable_item -> HelpClickableItemViewHolder.create(parent, onItemClicked)
        else -> super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*, *>, position: Int) = when (holder) {
        is HelpClickableItemViewHolder -> holder.bind(getItem(position) as HelpClickableItemViewHolder.UiModel)
        else -> super.onBindViewHolder(holder, position)
    }
}
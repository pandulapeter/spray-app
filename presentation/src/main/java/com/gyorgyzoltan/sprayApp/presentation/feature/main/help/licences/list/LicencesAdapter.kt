package com.gyorgyzoltan.sprayApp.presentation.feature.main.help.licences.list

import android.view.ViewGroup
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.presentation.R
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.list.BaseAdapter
import kotlinx.coroutines.CoroutineScope

internal class LicencesAdapter(
    scope: CoroutineScope,
    private val onLicenceSelected: (String) -> Unit
) : BaseAdapter<LicencesListItem>(scope) {

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is DependencyViewHolder.UiModel -> R.layout.item_licences_dependency
        else -> super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*, *> = when (viewType) {
        R.layout.item_licences_dependency -> DependencyViewHolder.create(parent, onLicenceSelected)
        else -> super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*, *>, position: Int) = when (holder) {
        is DependencyViewHolder -> holder.bind(getItem(position) as DependencyViewHolder.UiModel)
        else -> super.onBindViewHolder(holder, position)
    }
}
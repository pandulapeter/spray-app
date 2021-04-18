package com.gyorgyzoltan.sprayApp.help.licences.list

import android.view.ViewGroup
import com.gyorgyzoltan.sprayApp.help.R
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseAdapter
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import kotlinx.coroutines.CoroutineScope

internal class LicencesAdapter(
    scope: CoroutineScope,
    private val onLicenceSelected: (String) -> Unit
) : BaseAdapter<LicencesListItem>(scope) {

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is LicencesDependencyViewHolder.UiModel -> R.layout.item_licences_dependency
        is LicencesTextViewHolder.UiModel -> R.layout.item_licences_text
        else -> super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*, *> = when (viewType) {
        R.layout.item_licences_dependency -> LicencesDependencyViewHolder.create(parent, onLicenceSelected)
        R.layout.item_licences_text -> LicencesTextViewHolder.create(parent)
        else -> super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*, *>, position: Int) = when (holder) {
        is LicencesDependencyViewHolder -> holder.bind(getItem(position) as LicencesDependencyViewHolder.UiModel)
        is LicencesTextViewHolder -> holder.bind(getItem(position) as LicencesTextViewHolder.UiModel)
        else -> super.onBindViewHolder(holder, position)
    }
}
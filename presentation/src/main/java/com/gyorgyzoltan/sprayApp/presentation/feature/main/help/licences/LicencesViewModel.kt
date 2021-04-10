package com.gyorgyzoltan.sprayApp.presentation.feature.main.help.licences

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.ListViewModel
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.list.TextViewHolder
import com.gyorgyzoltan.sprayApp.model.dependency.Dependency
import com.gyorgyzoltan.sprayApp.model.dependency.DependencyType
import com.gyorgyzoltan.sprayApp.presentation.R
import com.gyorgyzoltan.sprayApp.presentation.feature.main.help.licences.list.DependencyViewHolder
import com.gyorgyzoltan.sprayApp.presentation.feature.main.help.licences.list.LicencesListItem

internal class LicencesViewModel : ListViewModel<LicencesListItem>() {

    override val items: LiveData<List<LicencesListItem>> = MutableLiveData(
        mutableListOf<LicencesListItem>().apply {
            add(TextViewHolder.UiModel(R.string.licences_description))
            DependencyType.values().forEach { type ->
                add(TextViewHolder.UiModel(type.titleResourceId))
                addAll(Dependency.values().filter { it.type == type }.sortedBy { it.title }.map { DependencyViewHolder.UiModel(it) })
            }
        }
    )
}
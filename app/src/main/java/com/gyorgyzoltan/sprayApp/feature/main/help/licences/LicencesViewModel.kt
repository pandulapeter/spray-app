package com.gyorgyzoltan.sprayApp.feature.main.help.licences

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gyorgyzoltan.sprayApp.R
import com.gyorgyzoltan.sprayApp.data.model.domain.Dependency
import com.gyorgyzoltan.sprayApp.feature.main.help.licences.list.DependencyViewHolder
import com.gyorgyzoltan.sprayApp.feature.main.help.licences.list.LicencesListItem
import com.gyorgyzoltan.sprayApp.feature.shared.ListViewModel
import com.gyorgyzoltan.sprayApp.feature.shared.list.TextViewHolder

class LicencesViewModel : ListViewModel<LicencesListItem>() {

    override val items: LiveData<List<LicencesListItem>> = MutableLiveData(
        mutableListOf<LicencesListItem>().apply {
            add(TextViewHolder.UiModel(R.string.licences_description))
            Dependency.Type.values().forEach { type ->
                add(TextViewHolder.UiModel(type.titleResourceId))
                addAll(Dependency.values().filter { it.type == type }.sortedBy { it.title }.map { DependencyViewHolder.UiModel(it) })
            }
        }
    )
}
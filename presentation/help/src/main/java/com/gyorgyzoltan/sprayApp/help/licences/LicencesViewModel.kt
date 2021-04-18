package com.gyorgyzoltan.sprayApp.help.licences

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gyorgyzoltan.sprayApp.help.licences.list.LicencesDependencyViewHolder
import com.gyorgyzoltan.sprayApp.help.licences.list.LicencesListItem
import com.gyorgyzoltan.sprayApp.help.licences.list.LicencesTextViewHolder
import com.gyorgyzoltan.sprayApp.main.R
import com.gyorgyzoltan.sprayApp.main.shared.list.ListViewModel
import com.gyorgyzoltan.sprayApp.model.dependency.Dependency
import com.gyorgyzoltan.sprayApp.model.dependency.DependencyType

internal class LicencesViewModel : ListViewModel<LicencesListItem>() {

    override val items: LiveData<List<LicencesListItem>> = MutableLiveData(
        mutableListOf<LicencesListItem>().apply {
            add(LicencesTextViewHolder.UiModel(R.string.licences_description))
            DependencyType.values().forEach { type ->
                add(LicencesTextViewHolder.UiModel(type.titleResourceId))
                addAll(Dependency.values().filter { it.type == type }.sortedBy { it.title }.map { LicencesDependencyViewHolder.UiModel(it) })
            }
        }
    )
}
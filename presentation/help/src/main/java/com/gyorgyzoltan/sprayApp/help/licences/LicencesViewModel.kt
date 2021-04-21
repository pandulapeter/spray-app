package com.gyorgyzoltan.sprayApp.help.licences

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gyorgyzoltan.sprayApp.domain.dependency.GetDependenciesUseCase
import com.gyorgyzoltan.sprayApp.help.licences.list.LicencesDependencyViewHolder
import com.gyorgyzoltan.sprayApp.help.licences.list.LicencesListItem
import com.gyorgyzoltan.sprayApp.help.licences.list.LicencesTextViewHolder
import com.gyorgyzoltan.sprayApp.main.R
import com.gyorgyzoltan.sprayApp.main.shared.list.ListViewModel

internal class LicencesViewModel(
    private val getDependencies: GetDependenciesUseCase
) : ListViewModel<LicencesListItem>() {

    override val items: LiveData<List<LicencesListItem>> = MutableLiveData(
        mutableListOf<LicencesListItem>().apply {
            add(LicencesTextViewHolder.UiModel(R.string.licences_description))
            val allDependencies = getDependencies()
            val allDependencyTypes = allDependencies.map { it.typeResourceId }.distinct()
            allDependencyTypes.forEach { typeResourceId ->
                add(LicencesTextViewHolder.UiModel(typeResourceId))
                addAll(allDependencies.filter { it.typeResourceId == typeResourceId }.sortedBy { it.title }.map { LicencesDependencyViewHolder.UiModel(it) })
            }
        }
    )
}
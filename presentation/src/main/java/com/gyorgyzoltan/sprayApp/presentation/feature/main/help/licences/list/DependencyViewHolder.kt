package com.gyorgyzoltan.sprayApp.presentation.feature.main.help.licences.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.model.dependency.Dependency
import com.gyorgyzoltan.sprayApp.presentation.R
import com.gyorgyzoltan.sprayApp.presentation.databinding.ItemLicencesDependencyBinding

internal class DependencyViewHolder private constructor(
    binding: ItemLicencesDependencyBinding,
    val onItemClicked: (String) -> Unit
) : BaseViewHolder<ItemLicencesDependencyBinding, DependencyViewHolder.UiModel>(binding) {

    init {
        binding.root.setOnClickListener {
            if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                binding.uiModel?.dependency?.url?.let { onItemClicked(it) }
            }
        }
    }

    data class UiModel(
        val dependency: Dependency
    ) : LicencesListItem {

        override val id = dependency.title
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onItemClicked: (String) -> Unit
        ) = DependencyViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_licences_dependency, parent, false),
            onItemClicked = onItemClicked
        )
    }
}
package com.gyorgyzoltan.sprayApp.help.licences.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gyorgyzoltan.sprayApp.help.R
import com.gyorgyzoltan.sprayApp.help.databinding.ItemLicencesDependencyBinding
import com.gyorgyzoltan.sprayApp.model.dependency.Dependency
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder

internal class LicencesDependencyViewHolder private constructor(
    binding: ItemLicencesDependencyBinding,
    val onItemClicked: (String) -> Unit
) : BaseViewHolder<ItemLicencesDependencyBinding, LicencesDependencyViewHolder.UiModel>(binding) {

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
        ) = LicencesDependencyViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_licences_dependency, parent, false),
            onItemClicked = onItemClicked
        )
    }
}
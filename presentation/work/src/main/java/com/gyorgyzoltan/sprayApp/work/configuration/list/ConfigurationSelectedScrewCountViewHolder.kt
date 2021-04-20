package com.gyorgyzoltan.sprayApp.work.configuration.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.work.R
import com.gyorgyzoltan.sprayApp.work.databinding.ItemConfigurationSelectedScrewCountBinding

internal class ConfigurationSelectedScrewCountViewHolder private constructor(
    binding: ItemConfigurationSelectedScrewCountBinding,
    onItemClicked: (Int) -> Unit
) : BaseViewHolder<ItemConfigurationSelectedScrewCountBinding, ConfigurationSelectedScrewCountViewHolder.UiModel>(binding) {

    init {
        binding.root.setOnClickListener {
            if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                onItemClicked(R.string.configuration_no_screw_count_set)
            }
        }
    }

    data class UiModel(
        val screwCount: Int
    ) : ConfigurationListItem {

        override val id = "selectedScrewCount"
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onItemClicked: (Int) -> Unit
        ) = ConfigurationSelectedScrewCountViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_configuration_selected_screw_count, parent, false),
            onItemClicked = onItemClicked
        )
    }
}
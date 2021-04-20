package com.gyorgyzoltan.sprayApp.work.configuration.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.work.R
import com.gyorgyzoltan.sprayApp.work.databinding.ItemConfigurationSelectedNozzleCountBinding

internal class ConfigurationSelectedNozzleCountViewHolder private constructor(
    binding: ItemConfigurationSelectedNozzleCountBinding,
    onItemClicked: (Int) -> Unit
) : BaseViewHolder<ItemConfigurationSelectedNozzleCountBinding, ConfigurationSelectedNozzleCountViewHolder.UiModel>(binding) {

    init {
        binding.root.setOnClickListener {
            if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                onItemClicked(R.string.configuration_no_nozzle_count_set)
            }
        }
    }

    data class UiModel(
        val nozzleCount: Int
    ) : ConfigurationListItem {

        override val id = "selectedNozzleCount"
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onItemClicked: (Int) -> Unit
        ) = ConfigurationSelectedNozzleCountViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_configuration_selected_nozzle_count, parent, false),
            onItemClicked = onItemClicked
        )
    }
}
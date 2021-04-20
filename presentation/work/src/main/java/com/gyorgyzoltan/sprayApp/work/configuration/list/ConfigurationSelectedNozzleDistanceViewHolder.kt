package com.gyorgyzoltan.sprayApp.work.configuration.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.work.R
import com.gyorgyzoltan.sprayApp.work.databinding.ItemConfigurationSelectedNozzleDistanceBinding

internal class ConfigurationSelectedNozzleDistanceViewHolder private constructor(
    binding: ItemConfigurationSelectedNozzleDistanceBinding,
    onItemClicked: (Int) -> Unit
) : BaseViewHolder<ItemConfigurationSelectedNozzleDistanceBinding, ConfigurationSelectedNozzleDistanceViewHolder.UiModel>(binding) {

    init {
        binding.root.setOnClickListener {
            if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                onItemClicked(R.string.configuration_no_nozzle_distance_set)
            }
        }
    }

    data class UiModel(
        val nozzleDistance: Float
    ) : ConfigurationListItem {

        override val id = "selectedNozzleDistance"
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onItemClicked: (Int) -> Unit
        ) = ConfigurationSelectedNozzleDistanceViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_configuration_selected_nozzle_distance, parent, false),
            onItemClicked = onItemClicked
        )
    }
}
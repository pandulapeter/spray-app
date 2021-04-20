package com.gyorgyzoltan.sprayApp.work.configuration.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.work.R
import com.gyorgyzoltan.sprayApp.work.databinding.ItemConfigurationSelectedWheelRadiusBinding

internal class ConfigurationSelectedWheelRadiusViewHolder private constructor(
    binding: ItemConfigurationSelectedWheelRadiusBinding,
    onItemClicked: (Int) -> Unit
) : BaseViewHolder<ItemConfigurationSelectedWheelRadiusBinding, ConfigurationSelectedWheelRadiusViewHolder.UiModel>(binding) {

    init {
        binding.root.setOnClickListener {
            if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                onItemClicked(R.string.configuration_no_wheel_radius_set)
            }
        }
    }

    data class UiModel(
        val wheelRadius: Float
    ) : ConfigurationListItem {

        override val id = "selectedWheelRadius"
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onItemClicked: (Int) -> Unit
        ) = ConfigurationSelectedWheelRadiusViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_configuration_selected_wheel_radius, parent, false),
            onItemClicked = onItemClicked
        )
    }
}
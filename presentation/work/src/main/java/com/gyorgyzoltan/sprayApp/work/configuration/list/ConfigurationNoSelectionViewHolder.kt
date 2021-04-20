package com.gyorgyzoltan.sprayApp.work.configuration.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.work.R
import com.gyorgyzoltan.sprayApp.work.databinding.ItemConfigurationNoSelectionBinding

internal class ConfigurationNoSelectionViewHolder private constructor(
    binding: ItemConfigurationNoSelectionBinding,
    onItemClicked: (Int) -> Unit
) : BaseViewHolder<ItemConfigurationNoSelectionBinding, ConfigurationNoSelectionViewHolder.UiModel>(binding) {

    init {
        binding.root.setOnClickListener {
            binding.uiModel?.let { uiModel ->
                if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                    onItemClicked(uiModel.hintResourceId)
                }
            }
        }
    }

    data class UiModel(
        @StringRes val hintResourceId: Int
    ) : ConfigurationListItem {

        override val id = "noSelection_$hintResourceId"
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onItemClicked: (Int) -> Unit
        ) = ConfigurationNoSelectionViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_configuration_no_selection, parent, false),
            onItemClicked = onItemClicked
        )
    }
}
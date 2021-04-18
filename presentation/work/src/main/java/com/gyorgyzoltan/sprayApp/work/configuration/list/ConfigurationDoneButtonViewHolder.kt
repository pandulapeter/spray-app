package com.gyorgyzoltan.sprayApp.work.configuration.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.work.R
import com.gyorgyzoltan.sprayApp.work.databinding.ItemConfigurationDoneButtonBinding

internal class ConfigurationDoneButtonViewHolder private constructor(
    binding: ItemConfigurationDoneButtonBinding,
    onItemClicked: () -> Unit
) : BaseViewHolder<ItemConfigurationDoneButtonBinding, ConfigurationDoneButtonViewHolder.UiModel>(binding) {

    init {
        binding.button.setOnClickListener {
            if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                onItemClicked()
            }
        }
    }

    data class UiModel(
        val isEnabled: Boolean
    ) : ConfigurationListItem {

        override val id: String = "doneButton"
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onItemClicked: () -> Unit
        ) = ConfigurationDoneButtonViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_configuration_done_button, parent, false),
            onItemClicked = onItemClicked
        )
    }
}
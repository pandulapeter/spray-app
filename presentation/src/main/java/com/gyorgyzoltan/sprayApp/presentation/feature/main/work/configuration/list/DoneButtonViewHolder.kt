package com.gyorgyzoltan.sprayApp.presentation.feature.main.work.configuration.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.presentation.R
import com.gyorgyzoltan.sprayApp.presentation.databinding.ItemConfigurationDoneButtonBinding

internal class DoneButtonViewHolder private constructor(
    binding: ItemConfigurationDoneButtonBinding,
    onItemClicked: () -> Unit
) : BaseViewHolder<ItemConfigurationDoneButtonBinding, DoneButtonViewHolder.UiModel>(binding) {

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
        ) = DoneButtonViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_configuration_done_button, parent, false),
            onItemClicked = onItemClicked
        )
    }
}
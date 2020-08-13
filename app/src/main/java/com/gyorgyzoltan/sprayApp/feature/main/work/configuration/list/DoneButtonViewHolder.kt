package com.gyorgyzoltan.sprayApp.feature.main.work.configuration.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gyorgyzoltan.sprayApp.R
import com.gyorgyzoltan.sprayApp.databinding.ItemConfigurationDoneButtonBinding
import com.gyorgyzoltan.sprayApp.feature.shared.list.BaseViewHolder

class DoneButtonViewHolder private constructor(
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
        override val id: String = "doneButton"
    ) : ConfigurationListItem

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
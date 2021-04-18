package com.gyorgyzoltan.sprayApp.presentation.feature.shared.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gyorgyzoltan.sprayApp.presentation.R
import com.gyorgyzoltan.sprayApp.presentation.databinding.ItemErrorBinding
import com.gyorgyzoltan.sprayApp.presentation.feature.main.help.licences.list.LicencesListItem
import com.gyorgyzoltan.sprayApp.presentation.feature.main.work.configuration.list.ConfigurationListItem
import com.gyorgyzoltan.sprayApp.presentation.feature.main.work.configuration.nozzlePicker.list.NozzlePickerListItem

internal class ErrorViewHolder private constructor(
    binding: ItemErrorBinding,
    onTryAgainButtonPressed: () -> Unit
) : BaseViewHolder<ItemErrorBinding, ErrorViewHolder.UiModel>(binding) {

    init {
        binding.tryAgainButton.setOnClickListener {
            if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                onTryAgainButtonPressed()
            }
        }
    }

    data class UiModel(
        override val id: String = "error"
    ) : ListItem, LicencesListItem, ConfigurationListItem, NozzlePickerListItem

    companion object {
        fun create(
            parent: ViewGroup,
            onTryAgainButtonPressed: () -> Unit
        ) = ErrorViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_error, parent, false),
            onTryAgainButtonPressed = onTryAgainButtonPressed
        )
    }
}
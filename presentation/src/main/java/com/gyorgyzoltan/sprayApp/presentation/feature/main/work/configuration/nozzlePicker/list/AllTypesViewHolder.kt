package com.gyorgyzoltan.sprayApp.presentation.feature.main.work.configuration.nozzlePicker.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gyorgyzoltan.sprayApp.presentation.R
import com.gyorgyzoltan.sprayApp.presentation.databinding.ItemNozzlePickerAllTypesBinding
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.list.BaseViewHolder

internal class AllTypesViewHolder private constructor(
    binding: ItemNozzlePickerAllTypesBinding,
    onSeeAllTypesButtonPressed: () -> Unit
) : BaseViewHolder<ItemNozzlePickerAllTypesBinding, AllTypesViewHolder.UiModel>(binding) {

    init {
        binding.seeAllTypesButton.setOnClickListener {
            if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                onSeeAllTypesButtonPressed()
            }
        }
    }

    data class UiModel(
        val nothing: Any? = null
    ) : NozzlePickerListItem {

        override val id = "allTypes"
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onSeeAllTypesButtonPressed: () -> Unit
        ) = AllTypesViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_nozzle_picker_all_types, parent, false),
            onSeeAllTypesButtonPressed = onSeeAllTypesButtonPressed
        )
    }
}
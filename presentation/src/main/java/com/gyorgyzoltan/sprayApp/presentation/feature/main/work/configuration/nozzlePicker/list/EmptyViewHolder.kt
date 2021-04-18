package com.gyorgyzoltan.sprayApp.presentation.feature.main.work.configuration.nozzlePicker.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleType
import com.gyorgyzoltan.sprayApp.presentation.R
import com.gyorgyzoltan.sprayApp.presentation.databinding.ItemNozzlePickerEmptyBinding
import com.gyorgyzoltan.sprayApp.presentation.databinding.ItemNozzlePickerHeaderBinding
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.list.BaseViewHolder

internal class EmptyViewHolder private constructor(
    binding: ItemNozzlePickerEmptyBinding
) : BaseViewHolder<ItemNozzlePickerEmptyBinding, EmptyViewHolder.UiModel>(binding) {

    data class UiModel(
        val selectedNozzleType: NozzleType
    ) : NozzlePickerListItem {

        override val id = "empty_${selectedNozzleType.name}"
    }

    companion object {
        fun create(
            parent: ViewGroup
        ) = EmptyViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_nozzle_picker_empty, parent, false)
        )
    }
}
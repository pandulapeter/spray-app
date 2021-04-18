package com.gyorgyzoltan.sprayApp.presentation.feature.main.work.configuration.nozzlePicker.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleType
import com.gyorgyzoltan.sprayApp.presentation.R
import com.gyorgyzoltan.sprayApp.presentation.databinding.ItemNozzlePickerHeaderBinding
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.list.BaseViewHolder

internal class HeaderViewHolder private constructor(
    binding: ItemNozzlePickerHeaderBinding
) : BaseViewHolder<ItemNozzlePickerHeaderBinding, HeaderViewHolder.UiModel>(binding) {

    data class UiModel(
        val selectedNozzleType: NozzleType
    ) : NozzlePickerListItem {

        override val id = "header_${selectedNozzleType.name}"
    }

    companion object {
        fun create(
            parent: ViewGroup
        ) = HeaderViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_nozzle_picker_header, parent, false)
        )
    }
}
package com.gyorgyzoltan.sprayApp.work.nozzlePicker.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.work.R
import com.gyorgyzoltan.sprayApp.work.databinding.ItemNozzlePickerEmptyStateBinding

internal class NozzlePickerEmptyStateViewHolder private constructor(
    binding: ItemNozzlePickerEmptyStateBinding
) : BaseViewHolder<ItemNozzlePickerEmptyStateBinding, NozzlePickerEmptyStateViewHolder.UiModel>(binding) {

    data class UiModel(
        val nothing: Any? = null
    ) : NozzlePickerListItem {

        override val id = "emptyState"
    }

    companion object {
        fun create(
            parent: ViewGroup
        ) = NozzlePickerEmptyStateViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_nozzle_picker_empty_state, parent, false)
        )
    }
}
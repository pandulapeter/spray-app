package com.gyorgyzoltan.sprayApp.work.nozzleCountPicker.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.work.R
import com.gyorgyzoltan.sprayApp.work.databinding.ItemNozzleCountHintBinding

class NozzleCountHintViewHolder private constructor(
    binding: ItemNozzleCountHintBinding
) : BaseViewHolder<ItemNozzleCountHintBinding, NozzleCountHintViewHolder.UiModel>(binding) {

    data class UiModel(
        val nothing: Any? = null
    ) : NozzleCountPickerListItem {

        override val id: String = "hint"
    }

    companion object {
        fun create(
            parent: ViewGroup
        ) = NozzleCountHintViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_nozzle_count_hint, parent, false)
        )
    }
}
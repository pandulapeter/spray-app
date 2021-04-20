package com.gyorgyzoltan.sprayApp.work.screwCountPicker.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.work.R
import com.gyorgyzoltan.sprayApp.work.databinding.ItemScrewCountHintBinding
import com.gyorgyzoltan.sprayApp.work.screwCountPicker.ScrewCountPickerViewModel

class ScrewCountHintViewHolder private constructor(
    binding: ItemScrewCountHintBinding
) : BaseViewHolder<ItemScrewCountHintBinding, ScrewCountHintViewHolder.UiModel>(binding) {

    data class UiModel(
        val nothing: Any? = null
    ) : ScrewCountPickerListItem {

        override val id: String = "hint"
        val minimumValue = ScrewCountPickerViewModel.MINIMUM_SCREW_COUNT
        val maximumValue = ScrewCountPickerViewModel.MAXIMUM_SCREW_COUNT
    }

    companion object {
        fun create(
            parent: ViewGroup
        ) = ScrewCountHintViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_screw_count_hint, parent, false)
        )
    }
}
package com.gyorgyzoltan.sprayApp.work.nozzleDistancePicker.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.work.R
import com.gyorgyzoltan.sprayApp.work.databinding.ItemNozzleDistanceHintBinding

class NozzleDistanceHintViewHolder private constructor(
    binding: ItemNozzleDistanceHintBinding
) : BaseViewHolder<ItemNozzleDistanceHintBinding, NozzleDistanceHintViewHolder.UiModel>(binding) {

    data class UiModel(
        val nothing: Any? = null
    ) : NozzleDistancePickerListItem {

        override val id: String = "hint"
    }

    companion object {
        fun create(
            parent: ViewGroup
        ) = NozzleDistanceHintViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_nozzle_distance_hint, parent, false)
        )
    }
}
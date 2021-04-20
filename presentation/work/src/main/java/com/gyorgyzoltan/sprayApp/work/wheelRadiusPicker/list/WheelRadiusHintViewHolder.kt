package com.gyorgyzoltan.sprayApp.work.wheelRadiusPicker.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.work.R
import com.gyorgyzoltan.sprayApp.work.databinding.ItemWheelRadiusHintBinding
import com.gyorgyzoltan.sprayApp.work.wheelRadiusPicker.WheelRadiusPickerViewModel

class WheelRadiusHintViewHolder private constructor(
    binding: ItemWheelRadiusHintBinding
) : BaseViewHolder<ItemWheelRadiusHintBinding, WheelRadiusHintViewHolder.UiModel>(binding) {

    data class UiModel(
        val nothing: Any? = null
    ) : WheelRadiusPickerListItem {

        override val id: String = "hint"
        val minimumValue = WheelRadiusPickerViewModel.MINIMUM_WHEEL_RADIUS
        val maximumValue = WheelRadiusPickerViewModel.MAXIMUM_WHEEL_RADIUS
    }

    companion object {
        fun create(
            parent: ViewGroup
        ) = WheelRadiusHintViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_wheel_radius_hint, parent, false)
        )
    }
}
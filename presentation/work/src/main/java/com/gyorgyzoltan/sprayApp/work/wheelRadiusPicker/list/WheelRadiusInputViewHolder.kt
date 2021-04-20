package com.gyorgyzoltan.sprayApp.work.wheelRadiusPicker.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.work.R
import com.gyorgyzoltan.sprayApp.work.databinding.ItemWheelRadiusInputBinding
import com.gyorgyzoltan.sprayApp.work.wheelRadiusPicker.WheelRadiusPickerViewModel
import kotlin.math.roundToInt

class WheelRadiusInputViewHolder private constructor(
    binding: ItemWheelRadiusInputBinding,
    onWheelRadiusChanged: (Float) -> Unit
) : BaseViewHolder<ItemWheelRadiusInputBinding, WheelRadiusInputViewHolder.UiModel>(binding) {

    init {
        binding.numberPicker.setOnValueChangedListener { _, oldVal, newVal ->
            if (bindingAdapterPosition != RecyclerView.NO_POSITION && oldVal != newVal && newVal != binding.uiModel?.value) {
                onWheelRadiusChanged(newVal.toFloat())
            }
        }
        binding.numberPicker.setFormatter {
            binding.root.context.getString(R.string.configuration_wheel_radius_format, it.toFloat())
        }
    }

    override fun bind(uiModel: UiModel) {
        super.bind(uiModel)
        binding.numberPicker.children.iterator().forEach { if (it is EditText) it.filters = arrayOfNulls(0) }
    }

    data class UiModel(
        val wheelRadius: Float
    ) : WheelRadiusPickerListItem {

        override val id: String = "input"
        val minimumValue = WheelRadiusPickerViewModel.MINIMUM_WHEEL_RADIUS.roundToInt()
        val maximumValue = WheelRadiusPickerViewModel.MAXIMUM_WHEEL_RADIUS.roundToInt()
        val value = wheelRadius.roundToInt()
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onWheelRadiusChanged: (Float) -> Unit
        ) = WheelRadiusInputViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_wheel_radius_input, parent, false),
            onWheelRadiusChanged = onWheelRadiusChanged
        )
    }
}
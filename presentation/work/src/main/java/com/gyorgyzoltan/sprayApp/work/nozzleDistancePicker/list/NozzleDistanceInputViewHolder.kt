package com.gyorgyzoltan.sprayApp.work.nozzleDistancePicker.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.work.R
import com.gyorgyzoltan.sprayApp.work.databinding.ItemNozzleDistanceInputBinding
import com.gyorgyzoltan.sprayApp.work.nozzleDistancePicker.NozzleDistancePickerViewModel
import kotlin.math.roundToInt

class NozzleDistanceInputViewHolder private constructor(
    binding: ItemNozzleDistanceInputBinding,
    onNozzleDistanceChanged: (Float) -> Unit
) : BaseViewHolder<ItemNozzleDistanceInputBinding, NozzleDistanceInputViewHolder.UiModel>(binding) {

    init {
        binding.numberPicker.run {
            setOnValueChangedListener { _, oldVal, newVal ->
                if (bindingAdapterPosition != RecyclerView.NO_POSITION && oldVal != newVal && newVal != binding.uiModel?.value) {
                    onNozzleDistanceChanged(newVal.toFloat())
                }
            }
            setFormatter { context.getString(R.string.configuration_nozzle_distance_format, it.toFloat()) }
        }
    }

    override fun bind(uiModel: UiModel) {
        super.bind(uiModel)
        binding.numberPicker.children.iterator().forEach { if (it is EditText) it.filters = arrayOfNulls(0) }
    }

    data class UiModel(
        val nozzleDistance: Float
    ) : NozzleDistancePickerListItem {

        override val id: String = "input"
        val minimumValue = NozzleDistancePickerViewModel.MINIMUM_NOZZLE_DISTANCE.roundToInt()
        val maximumValue = NozzleDistancePickerViewModel.MAXIMUM_NOZZLE_DISTANCE.roundToInt()
        val value = nozzleDistance.roundToInt()
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onNozzleDistanceChanged: (Float) -> Unit
        ) = NozzleDistanceInputViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_nozzle_distance_input, parent, false),
            onNozzleDistanceChanged = onNozzleDistanceChanged
        )
    }
}
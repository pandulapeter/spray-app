package com.gyorgyzoltan.sprayApp.work.nozzleCountPicker.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.work.R
import com.gyorgyzoltan.sprayApp.work.databinding.ItemNozzleCountInputBinding
import com.gyorgyzoltan.sprayApp.work.nozzleCountPicker.NozzleCountPickerViewModel

class NozzleCountInputViewHolder private constructor(
    binding: ItemNozzleCountInputBinding,
    onNozzleCountChanged: (Int) -> Unit
) : BaseViewHolder<ItemNozzleCountInputBinding, NozzleCountInputViewHolder.UiModel>(binding) {

    init {
        binding.numberPicker.run {
            setOnValueChangedListener { _, oldVal, newVal ->
                if (bindingAdapterPosition != RecyclerView.NO_POSITION && oldVal != newVal && newVal != binding.uiModel?.nozzleCount) {
                    onNozzleCountChanged(newVal)
                }
            }
            setFormatter { context.resources.getQuantityString(R.plurals.configuration_nozzle_count_format, it, it) }
        }
    }

    override fun bind(uiModel: UiModel) {
        super.bind(uiModel)
        binding.numberPicker.run {
            (0 until childCount).forEach { index ->
                getChildAt(index).let { if (it is EditText) it.filters = arrayOfNulls(0) }
            }
        }
    }

    data class UiModel(
        val nozzleCount: Int
    ) : NozzleCountPickerListItem {

        override val id: String = "input"
        val minimumValue = NozzleCountPickerViewModel.MINIMUM_NOZZLE_COUNT
        val maximumValue = NozzleCountPickerViewModel.MAXIMUM_NOZZLE_COUNT
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onNozzleCountChanged: (Int) -> Unit
        ) = NozzleCountInputViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_nozzle_count_input, parent, false),
            onNozzleCountChanged = onNozzleCountChanged
        )
    }
}
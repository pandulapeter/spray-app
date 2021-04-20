package com.gyorgyzoltan.sprayApp.work.screwCountPicker.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.work.R
import com.gyorgyzoltan.sprayApp.work.databinding.ItemScrewCountInputBinding
import com.gyorgyzoltan.sprayApp.work.screwCountPicker.ScrewCountPickerViewModel

class ScrewCountInputViewHolder private constructor(
    binding: ItemScrewCountInputBinding,
    onScrewCountChanged: (Int) -> Unit
) : BaseViewHolder<ItemScrewCountInputBinding, ScrewCountInputViewHolder.UiModel>(binding) {

    init {
        binding.numberPicker.run {
            setOnValueChangedListener { _, oldVal, newVal ->
                if (bindingAdapterPosition != RecyclerView.NO_POSITION && oldVal != newVal && newVal != binding.uiModel?.screwCount) {
                    onScrewCountChanged(newVal)
                }
            }
            setFormatter { context.resources.getQuantityString(R.plurals.configuration_screw_count_format, it, it) }
        }
    }

    override fun bind(uiModel: UiModel) {
        super.bind(uiModel)
        binding.numberPicker.children.iterator().forEach { if (it is EditText) it.filters = arrayOfNulls(0) }
    }

    data class UiModel(
        val screwCount: Int
    ) : ScrewCountPickerListItem {

        override val id: String = "input"
        val minimumValue = ScrewCountPickerViewModel.MINIMUM_SCREW_COUNT
        val maximumValue = ScrewCountPickerViewModel.MAXIMUM_SCREW_COUNT
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onScrewCountChanged: (Int) -> Unit
        ) = ScrewCountInputViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_screw_count_input, parent, false),
            onScrewCountChanged = onScrewCountChanged
        )
    }
}
package com.gyorgyzoltan.sprayApp.work.nozzlePicker.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleType
import com.gyorgyzoltan.sprayApp.work.R
import com.gyorgyzoltan.sprayApp.work.databinding.ItemNozzlePickerNozzleTypeBinding

internal class NozzlePickerNozzleTypeViewHolder private constructor(
    binding: ItemNozzlePickerNozzleTypeBinding,
    onItemClicked: (NozzleType) -> Unit
) : BaseViewHolder<ItemNozzlePickerNozzleTypeBinding, NozzlePickerNozzleTypeViewHolder.UiModel>(binding) {

    init {
        binding.root.setOnClickListener {
            binding.uiModel?.let { uiModel ->
                if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                    onItemClicked(uiModel.nozzleType)
                }
            }
        }
    }

    data class UiModel(
        val nozzleType: NozzleType,
        val isExpanded: Boolean
    ) : NozzlePickerListItem {

        override val id = nozzleType.name
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onItemClicked: (NozzleType) -> Unit
        ) = NozzlePickerNozzleTypeViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_nozzle_picker_nozzle_type, parent, false),
            onItemClicked = onItemClicked
        )
    }
}
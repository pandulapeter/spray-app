package com.gyorgyzoltan.sprayApp.presentation.feature.main.work.configuration.nozzlePicker.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleType
import com.gyorgyzoltan.sprayApp.presentation.R
import com.gyorgyzoltan.sprayApp.presentation.databinding.ItemNozzlePickerNozzleTypeBinding
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.list.BaseViewHolder

internal class NozzleTypeViewHolder private constructor(
    binding: ItemNozzlePickerNozzleTypeBinding,
    onItemClicked: (NozzleType) -> Unit
) : BaseViewHolder<ItemNozzlePickerNozzleTypeBinding, NozzleTypeViewHolder.UiModel>(binding) {

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
        val nozzleType: NozzleType
    ) : NozzlePickerListItem {

        override val id = nozzleType.name
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onItemClicked: (NozzleType) -> Unit
        ) = NozzleTypeViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_nozzle_picker_nozzle_type, parent, false),
            onItemClicked = onItemClicked
        )
    }
}
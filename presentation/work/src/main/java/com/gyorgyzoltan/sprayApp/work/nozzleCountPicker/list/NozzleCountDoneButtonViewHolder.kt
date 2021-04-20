package com.gyorgyzoltan.sprayApp.work.nozzleCountPicker.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.work.R
import com.gyorgyzoltan.sprayApp.work.databinding.ItemNozzleCountDoneButtonBinding

internal class NozzleCountDoneButtonViewHolder private constructor(
    binding: ItemNozzleCountDoneButtonBinding,
    onItemClicked: () -> Unit
) : BaseViewHolder<ItemNozzleCountDoneButtonBinding, NozzleCountDoneButtonViewHolder.UiModel>(binding) {

    init {
        binding.button.setOnClickListener {
            if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                onItemClicked()
            }
        }
    }

    data class UiModel(
        val isEnabled: Boolean
    ) : NozzleCountPickerListItem {

        override val id: String = "doneButton"
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onItemClicked: () -> Unit
        ) = NozzleCountDoneButtonViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_nozzle_count_done_button, parent, false),
            onItemClicked = onItemClicked
        )
    }
}
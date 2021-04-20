package com.gyorgyzoltan.sprayApp.work.screwCountPicker.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.work.R
import com.gyorgyzoltan.sprayApp.work.databinding.ItemScrewCountDoneButtonBinding

internal class ScrewCountDoneButtonViewHolder private constructor(
    binding: ItemScrewCountDoneButtonBinding,
    onItemClicked: () -> Unit
) : BaseViewHolder<ItemScrewCountDoneButtonBinding, ScrewCountDoneButtonViewHolder.UiModel>(binding) {

    init {
        binding.button.setOnClickListener {
            if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                onItemClicked()
            }
        }
    }

    data class UiModel(
        val isEnabled: Boolean
    ) : ScrewCountPickerListItem {

        override val id: String = "doneButton"
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onItemClicked: () -> Unit
        ) = ScrewCountDoneButtonViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_screw_count_done_button, parent, false),
            onItemClicked = onItemClicked
        )
    }
}
package com.gyorgyzoltan.sprayApp.work.nozzleDistancePicker.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.work.R
import com.gyorgyzoltan.sprayApp.work.databinding.ItemNozzleDistanceDoneButtonBinding

internal class NozzleDistanceDoneButtonViewHolder private constructor(
    binding: ItemNozzleDistanceDoneButtonBinding,
    onItemClicked: () -> Unit
) : BaseViewHolder<ItemNozzleDistanceDoneButtonBinding, NozzleDistanceDoneButtonViewHolder.UiModel>(binding) {

    init {
        binding.button.setOnClickListener {
            if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                onItemClicked()
            }
        }
    }

    data class UiModel(
        val isEnabled: Boolean
    ) : NozzleDistancePickerListItem {

        override val id: String = "doneButton"
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onItemClicked: () -> Unit
        ) = NozzleDistanceDoneButtonViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_nozzle_distance_done_button, parent, false),
            onItemClicked = onItemClicked
        )
    }
}
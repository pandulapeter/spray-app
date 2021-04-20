package com.gyorgyzoltan.sprayApp.work.wheelRadiusPicker.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.work.R
import com.gyorgyzoltan.sprayApp.work.databinding.ItemWheelRadiusDoneButtonBinding

internal class WheelRadiusDoneButtonViewHolder private constructor(
    binding: ItemWheelRadiusDoneButtonBinding,
    onItemClicked: () -> Unit
) : BaseViewHolder<ItemWheelRadiusDoneButtonBinding, WheelRadiusDoneButtonViewHolder.UiModel>(binding) {

    init {
        binding.button.setOnClickListener {
            if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                onItemClicked()
            }
        }
    }

    data class UiModel(
        val isEnabled: Boolean
    ) : WheelRadiusPickerListItem {

        override val id: String = "doneButton"
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onItemClicked: () -> Unit
        ) = WheelRadiusDoneButtonViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_wheel_radius_done_button, parent, false),
            onItemClicked = onItemClicked
        )
    }
}
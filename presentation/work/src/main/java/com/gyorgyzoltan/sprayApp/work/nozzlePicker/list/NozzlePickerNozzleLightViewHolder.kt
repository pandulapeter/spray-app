package com.gyorgyzoltan.sprayApp.work.nozzlePicker.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.main.shared.utilities.color
import com.gyorgyzoltan.sprayApp.model.nozzle.Nozzle
import com.gyorgyzoltan.sprayApp.work.R
import com.gyorgyzoltan.sprayApp.work.databinding.ItemNozzlePickerNozzleLightBinding

internal class NozzlePickerNozzleLightViewHolder private constructor(
    binding: ItemNozzlePickerNozzleLightBinding,
    onItemClicked: (Nozzle) -> Unit
) : BaseViewHolder<ItemNozzlePickerNozzleLightBinding, NozzlePickerNozzleLightViewHolder.UiModel>(binding) {

    init {
        binding.root.setOnClickListener {
            binding.uiModel?.let { uiModel ->
                if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                    onItemClicked(uiModel.nozzle)
                }
            }
        }
    }

    override fun bind(uiModel: UiModel) {
        super.bind(uiModel)
        binding.card.setCardBackgroundColor(itemView.context.color(uiModel.nozzle.color.value))
    }

    data class UiModel(
        val nozzle: Nozzle
    ) : NozzlePickerListItem {

        override val id = nozzle.name
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onItemClicked: (Nozzle) -> Unit
        ) = NozzlePickerNozzleLightViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_nozzle_picker_nozzle_light, parent, false),
            onItemClicked = onItemClicked
        )
    }
}
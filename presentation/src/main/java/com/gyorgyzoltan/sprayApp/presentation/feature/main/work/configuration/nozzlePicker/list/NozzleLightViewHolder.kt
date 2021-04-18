package com.gyorgyzoltan.sprayApp.presentation.feature.main.work.configuration.nozzlePicker.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gyorgyzoltan.sprayApp.model.nozzle.Nozzle
import com.gyorgyzoltan.sprayApp.presentation.R
import com.gyorgyzoltan.sprayApp.presentation.databinding.ItemNozzlePickerNozzleLightBinding
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.presentation.utils.color

internal class NozzleLightViewHolder private constructor(
    binding: ItemNozzlePickerNozzleLightBinding,
    onItemClicked: (Nozzle) -> Unit
) : BaseViewHolder<ItemNozzlePickerNozzleLightBinding, NozzleLightViewHolder.UiModel>(binding) {

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
        ) = NozzleLightViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_nozzle_picker_nozzle_light, parent, false),
            onItemClicked = onItemClicked
        )
    }
}
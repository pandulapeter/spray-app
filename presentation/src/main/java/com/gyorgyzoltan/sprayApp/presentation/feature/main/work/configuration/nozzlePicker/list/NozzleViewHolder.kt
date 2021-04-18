package com.gyorgyzoltan.sprayApp.presentation.feature.main.work.configuration.nozzlePicker.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gyorgyzoltan.sprayApp.model.nozzle.Nozzle
import com.gyorgyzoltan.sprayApp.presentation.R
import com.gyorgyzoltan.sprayApp.presentation.databinding.ItemNozzlePickerNozzleBinding
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.presentation.utils.color

internal class NozzleViewHolder private constructor(
    binding: ItemNozzlePickerNozzleBinding,
    onItemClicked: (Nozzle) -> Unit
) : BaseViewHolder<ItemNozzlePickerNozzleBinding, NozzleViewHolder.UiModel>(binding) {

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
        itemView.setBackgroundColor(itemView.context.color(uiModel.nozzle.color.value))
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
        ) = NozzleViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_nozzle_picker_nozzle, parent, false),
            onItemClicked = onItemClicked
        )
    }
}
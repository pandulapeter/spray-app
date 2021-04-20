package com.gyorgyzoltan.sprayApp.work.configuration.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.main.shared.utilities.color
import com.gyorgyzoltan.sprayApp.model.nozzle.Nozzle
import com.gyorgyzoltan.sprayApp.work.R
import com.gyorgyzoltan.sprayApp.work.databinding.ItemConfigurationSelectedNozzleDarkBinding

internal class ConfigurationSelectedNozzleDarkViewHolder private constructor(
    binding: ItemConfigurationSelectedNozzleDarkBinding,
    onItemClicked: () -> Unit
) : BaseViewHolder<ItemConfigurationSelectedNozzleDarkBinding, ConfigurationSelectedNozzleDarkViewHolder.UiModel>(binding) {

    init {
        binding.root.setOnClickListener {
            if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                onItemClicked()
            }
        }
    }

    override fun bind(uiModel: UiModel) {
        super.bind(uiModel)
        binding.card.setCardBackgroundColor(itemView.context.color(uiModel.nozzle?.color?.value ?: R.color.transparent))
    }

    data class UiModel(
        val nozzle: Nozzle?
    ) : ConfigurationListItem {

        override val id = "selectedNozzleDark"
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onItemClicked: () -> Unit
        ) = ConfigurationSelectedNozzleDarkViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_configuration_selected_nozzle_dark, parent, false),
            onItemClicked = onItemClicked
        )
    }
}
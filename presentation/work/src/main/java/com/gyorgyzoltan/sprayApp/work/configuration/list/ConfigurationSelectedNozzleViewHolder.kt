package com.gyorgyzoltan.sprayApp.work.configuration.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.main.shared.utilities.color
import com.gyorgyzoltan.sprayApp.model.nozzle.Nozzle
import com.gyorgyzoltan.sprayApp.work.R
import com.gyorgyzoltan.sprayApp.work.databinding.ItemConfigurationSelectedNozzleBinding

internal class ConfigurationSelectedNozzleViewHolder private constructor(
    binding: ItemConfigurationSelectedNozzleBinding,
    onItemClicked: () -> Unit
) : BaseViewHolder<ItemConfigurationSelectedNozzleBinding, ConfigurationSelectedNozzleViewHolder.UiModel>(binding) {

    init {
        binding.root.setOnClickListener {
            if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                onItemClicked()
            }
        }
    }

    override fun bind(uiModel: UiModel) {
        super.bind(uiModel)
        binding.nozzle.setBackgroundColor(itemView.context.color(uiModel.nozzle?.color?.value ?: R.color.transparent))
    }

    data class UiModel(
        val nozzle: Nozzle?
    ) : ConfigurationListItem {

        override val id = "selectedNozzle"
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onItemClicked: () -> Unit
        ) = ConfigurationSelectedNozzleViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_configuration_selected_nozzle, parent, false),
            onItemClicked = onItemClicked
        )
    }
}
package com.gyorgyzoltan.sprayApp.presentation.feature.main.work.configuration.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.model.nozzle.Nozzle
import com.gyorgyzoltan.sprayApp.presentation.R
import com.gyorgyzoltan.sprayApp.presentation.databinding.ItemConfigurationSelectedNozzleBinding
import com.gyorgyzoltan.sprayApp.presentation.utils.color

internal class SelectedNozzleViewHolder private constructor(
    binding: ItemConfigurationSelectedNozzleBinding,
    onItemClicked: () -> Unit
) : BaseViewHolder<ItemConfigurationSelectedNozzleBinding, SelectedNozzleViewHolder.UiModel>(binding) {

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
        val formattedName = nozzle?.name ?: "Unselected"
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onItemClicked: () -> Unit
        ) = SelectedNozzleViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_configuration_selected_nozzle, parent, false),
            onItemClicked = onItemClicked
        )
    }
}
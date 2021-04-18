package com.gyorgyzoltan.sprayApp.help.overview.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gyorgyzoltan.sprayApp.help.R
import com.gyorgyzoltan.sprayApp.help.databinding.ItemHelpClickableItemBinding
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.main.shared.utilities.tintedDrawable

internal class HelpClickableItemViewHolder private constructor(
    binding: ItemHelpClickableItemBinding,
    onItemClicked: (UiModel) -> Unit
) : BaseViewHolder<ItemHelpClickableItemBinding, HelpClickableItemViewHolder.UiModel>(binding) {

    init {
        binding.root.setOnClickListener {
            if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                binding.uiModel?.let(onItemClicked)
            }
        }
    }

    override fun bind(uiModel: UiModel) = binding.title.run {
        super.bind(uiModel)
        setCompoundDrawablesWithIntrinsicBounds(itemView.context.tintedDrawable(uiModel.drawableResourceId, binding.title.textColors.defaultColor), null, null, null)
    }

    data class UiModel(
        @StringRes val textResourceId: Int,
        @DrawableRes val drawableResourceId: Int,
        override val id: String = "clickableItem_$textResourceId"
    ) : HelpListItem

    companion object {
        fun create(
            parent: ViewGroup,
            onItemClicked: (UiModel) -> Unit
        ) = HelpClickableItemViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_help_clickable_item, parent, false),
            onItemClicked = onItemClicked
        )
    }
}
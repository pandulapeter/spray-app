package com.gyorgyzoltan.sprayApp.work.configuration.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.work.R
import com.gyorgyzoltan.sprayApp.work.databinding.ItemConfigurationTextBinding

class ConfigurationTextViewHolder private constructor(
    binding: ItemConfigurationTextBinding
) : BaseViewHolder<ItemConfigurationTextBinding, ConfigurationTextViewHolder.UiModel>(binding) {

    data class UiModel(
        @StringRes val textResourceId: Int,
        override val id: String = "text_$textResourceId"
    ) : ConfigurationListItem

    companion object {
        fun create(
            parent: ViewGroup
        ) = ConfigurationTextViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_configuration_text, parent, false)
        )
    }
}
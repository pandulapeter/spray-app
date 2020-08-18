package com.gyorgyzoltan.sprayApp.feature.shared.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import com.gyorgyzoltan.sprayApp.R
import com.gyorgyzoltan.sprayApp.databinding.ItemTextBinding
import com.gyorgyzoltan.sprayApp.feature.main.help.licences.list.LicencesListItem
import com.gyorgyzoltan.sprayApp.feature.main.work.configuration.list.ConfigurationListItem
import com.gyorgyzoltan.sprayApp.feature.main.work.configuration.nozzlePicker.list.NozzlePickerListItem

class TextViewHolder private constructor(
    binding: ItemTextBinding
) : BaseViewHolder<ItemTextBinding, TextViewHolder.UiModel>(binding) {

    data class UiModel(
        @StringRes val textResourceId: Int,
        override val id: String = "text_$textResourceId"
    ) : ListItem, LicencesListItem, ConfigurationListItem, NozzlePickerListItem

    companion object {
        fun create(
            parent: ViewGroup
        ) = TextViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_text, parent, false)
        )
    }
}
package com.gyorgyzoltan.sprayApp.help.licences.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import com.gyorgyzoltan.sprayApp.help.R
import com.gyorgyzoltan.sprayApp.help.databinding.ItemLicencesTextBinding
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder

class LicencesTextViewHolder private constructor(
    binding: ItemLicencesTextBinding
) : BaseViewHolder<ItemLicencesTextBinding, LicencesTextViewHolder.UiModel>(binding) {

    data class UiModel(
        @StringRes val textResourceId: Int,
        override val id: String = "text_$textResourceId"
    ) : LicencesListItem

    companion object {
        fun create(
            parent: ViewGroup
        ) = LicencesTextViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_licences_text, parent, false)
        )
    }
}
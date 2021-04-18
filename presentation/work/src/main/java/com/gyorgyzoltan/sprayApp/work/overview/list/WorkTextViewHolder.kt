package com.gyorgyzoltan.sprayApp.work.overview.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.work.R
import com.gyorgyzoltan.sprayApp.work.databinding.ItemWorkTextBinding

class WorkTextViewHolder private constructor(
    binding: ItemWorkTextBinding
) : BaseViewHolder<ItemWorkTextBinding, WorkTextViewHolder.UiModel>(binding) {

    data class UiModel(
        @StringRes val textResourceId: Int,
        override val id: String = "text_$textResourceId"
    ) : WorkListItem

    companion object {
        fun create(
            parent: ViewGroup
        ) = WorkTextViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_work_text, parent, false)
        )
    }
}
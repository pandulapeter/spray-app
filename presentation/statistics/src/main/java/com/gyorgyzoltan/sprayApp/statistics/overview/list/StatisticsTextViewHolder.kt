package com.gyorgyzoltan.sprayApp.statistics.overview.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import com.gyorgyzoltan.sprayApp.main.shared.list.BaseViewHolder
import com.gyorgyzoltan.sprayApp.statistics.R
import com.gyorgyzoltan.sprayApp.statistics.databinding.ItemStatisticsTextBinding

class StatisticsTextViewHolder private constructor(
    binding: ItemStatisticsTextBinding
) : BaseViewHolder<ItemStatisticsTextBinding, StatisticsTextViewHolder.UiModel>(binding) {

    data class UiModel(
        @StringRes val textResourceId: Int,
        override val id: String = "text_$textResourceId"
    ) : StatisticsListItem

    companion object {
        fun create(
            parent: ViewGroup
        ) = StatisticsTextViewHolder(
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_statistics_text, parent, false)
        )
    }
}
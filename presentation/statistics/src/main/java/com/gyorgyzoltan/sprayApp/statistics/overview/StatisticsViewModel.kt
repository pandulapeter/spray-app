package com.gyorgyzoltan.sprayApp.statistics.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gyorgyzoltan.sprayApp.main.R
import com.gyorgyzoltan.sprayApp.main.shared.list.ListViewModel
import com.gyorgyzoltan.sprayApp.statistics.overview.list.StatisticsListItem
import com.gyorgyzoltan.sprayApp.statistics.overview.list.StatisticsTextViewHolder

internal class StatisticsViewModel : ListViewModel<StatisticsListItem>() {

    override val items: LiveData<List<StatisticsListItem>> = MutableLiveData(
        listOf(
            StatisticsTextViewHolder.UiModel(R.string.statistics_placeholder)
        )
    )
}
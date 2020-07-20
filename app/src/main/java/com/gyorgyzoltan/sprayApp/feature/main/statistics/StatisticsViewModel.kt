package com.gyorgyzoltan.sprayApp.feature.main.statistics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gyorgyzoltan.sprayApp.R
import com.gyorgyzoltan.sprayApp.feature.shared.ListViewModel
import com.gyorgyzoltan.sprayApp.feature.shared.list.ListItem
import com.gyorgyzoltan.sprayApp.feature.shared.list.TextViewHolder

class StatisticsViewModel : ListViewModel<ListItem>() {

    override val items: LiveData<List<ListItem>> = MutableLiveData(
        listOf(
            TextViewHolder.UiModel(R.string.statistics_placeholder)
        )
    )
}
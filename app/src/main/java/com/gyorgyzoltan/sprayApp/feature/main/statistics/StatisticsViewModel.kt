package com.gyorgyzoltan.sprayApp.feature.main.statistics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gyorgyzoltan.sprayApp.feature.shared.ListViewModel
import com.gyorgyzoltan.sprayApp.feature.shared.list.ListItem

class StatisticsViewModel : ListViewModel<ListItem>() {

    override val items: LiveData<List<ListItem>> = MutableLiveData(emptyList())
}
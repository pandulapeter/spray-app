package com.gyorgyzoltan.sprayApp.feature.main.statistics

import androidx.lifecycle.viewModelScope
import com.gyorgyzoltan.sprayApp.R
import com.gyorgyzoltan.sprayApp.feature.shared.ListFragment
import com.gyorgyzoltan.sprayApp.feature.shared.list.BaseAdapter
import com.gyorgyzoltan.sprayApp.feature.shared.list.ListItem
import org.koin.androidx.viewmodel.ext.android.viewModel

class StatisticsFragment : ListFragment<StatisticsViewModel, ListItem>(R.string.main_statistics) {

    override val viewModel by viewModel<StatisticsViewModel>()

    override fun createAdapter() = object : BaseAdapter<ListItem>(viewModel.viewModelScope) {}

    companion object {
        fun newInstance() = StatisticsFragment()
    }
}
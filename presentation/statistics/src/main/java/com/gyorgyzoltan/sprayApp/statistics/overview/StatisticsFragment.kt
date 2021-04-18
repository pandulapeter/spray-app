package com.gyorgyzoltan.sprayApp.statistics.overview

import androidx.lifecycle.viewModelScope
import com.gyorgyzoltan.sprayApp.main.R
import com.gyorgyzoltan.sprayApp.main.shared.list.ListFragment
import com.gyorgyzoltan.sprayApp.statistics.overview.list.StatisticsAdapter
import com.gyorgyzoltan.sprayApp.statistics.overview.list.StatisticsListItem
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class StatisticsFragment : ListFragment<StatisticsViewModel, StatisticsListItem>(R.string.main_statistics) {

    override val viewModel by viewModel<StatisticsViewModel>()

    override fun createAdapter() = StatisticsAdapter(viewModel.viewModelScope)

    companion object {
        fun newInstance() = StatisticsFragment()
    }
}
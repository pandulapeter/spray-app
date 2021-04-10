package com.gyorgyzoltan.sprayApp.presentation.feature.main.statistics

import androidx.lifecycle.viewModelScope
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.list.ListItem
import com.gyorgyzoltan.sprayApp.presentation.R
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.ListFragment
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.list.BaseAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class StatisticsFragment : ListFragment<StatisticsViewModel, ListItem>(R.string.main_statistics) {

    override val viewModel by viewModel<StatisticsViewModel>()

    override fun createAdapter() = object : BaseAdapter<ListItem>(viewModel.viewModelScope) {}

    companion object {
        fun newInstance() = StatisticsFragment()
    }
}
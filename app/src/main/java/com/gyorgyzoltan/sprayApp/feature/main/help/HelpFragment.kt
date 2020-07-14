package com.gyorgyzoltan.sprayApp.feature.main.help

import androidx.lifecycle.viewModelScope
import com.gyorgyzoltan.sprayApp.R
import com.gyorgyzoltan.sprayApp.feature.shared.ListFragment
import com.gyorgyzoltan.sprayApp.feature.shared.list.BaseAdapter
import com.gyorgyzoltan.sprayApp.feature.shared.list.ListItem
import org.koin.androidx.viewmodel.ext.android.viewModel

class HelpFragment : ListFragment<HelpViewModel, ListItem>(R.string.main_help) {

    override val viewModel by viewModel<HelpViewModel>()

    override fun createAdapter() = object : BaseAdapter<ListItem>(viewModel.viewModelScope) {}

    companion object {
        fun newInstance() = HelpFragment()
    }
}
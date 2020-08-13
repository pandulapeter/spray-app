package com.gyorgyzoltan.sprayApp.feature.main.work

import androidx.lifecycle.viewModelScope
import com.gyorgyzoltan.sprayApp.R
import com.gyorgyzoltan.sprayApp.feature.shared.ListFragment
import com.gyorgyzoltan.sprayApp.feature.shared.list.BaseAdapter
import com.gyorgyzoltan.sprayApp.feature.shared.list.ListItem
import org.koin.androidx.viewmodel.ext.android.viewModel

class WorkFragment : ListFragment<WorkViewModel, ListItem>(R.string.main_work) {

    override val viewModel by viewModel<WorkViewModel>()
    override val actions = listOf(
        R.drawable.ic_configuration to ::navigateToConfiguration
    )

    override fun createAdapter() = object : BaseAdapter<ListItem>(viewModel.viewModelScope) {}

    private fun navigateToConfiguration() {
        (parentFragment as? WorkContainerFragment?)?.navigateToConfiguration()
    }

    companion object {
        fun newInstance() = WorkFragment()
    }
}
package com.gyorgyzoltan.sprayApp.work.overview

import androidx.lifecycle.viewModelScope
import com.gyorgyzoltan.sprayApp.main.R
import com.gyorgyzoltan.sprayApp.main.shared.list.ListFragment
import com.gyorgyzoltan.sprayApp.work.overview.list.WorkAdapter
import com.gyorgyzoltan.sprayApp.work.overview.list.WorkListItem
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class WorkFragment : ListFragment<WorkViewModel, WorkListItem>(R.string.main_work) {

    override val viewModel by viewModel<WorkViewModel>()
    override val actions = listOf(
        R.drawable.ic_configuration to ::navigateToConfiguration
    )

    override fun createAdapter() = WorkAdapter(viewModel.viewModelScope)

    private fun navigateToConfiguration() {
        (parentFragment as? WorkContainerFragment?)?.navigateToConfiguration()
    }

    companion object {
        fun newInstance() = WorkFragment()
    }
}
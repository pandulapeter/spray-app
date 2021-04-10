package com.gyorgyzoltan.sprayApp.presentation.feature.main.help.licences

import androidx.lifecycle.viewModelScope
import com.gyorgyzoltan.sprayApp.presentation.R
import com.gyorgyzoltan.sprayApp.presentation.feature.main.help.licences.list.LicencesAdapter
import com.gyorgyzoltan.sprayApp.presentation.feature.main.help.licences.list.LicencesListItem
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.ListFragment
import com.gyorgyzoltan.sprayApp.presentation.utils.openUrl
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class LicencesFragment : ListFragment<LicencesViewModel, LicencesListItem>(R.string.licences_title) {

    override val viewModel by viewModel<LicencesViewModel>()

    override fun createAdapter() = LicencesAdapter(viewModel.viewModelScope, ::onLicenceSelected)

    private fun onLicenceSelected(url: String) = binding.recyclerView.openUrl(url)

    companion object {
        fun newInstance() = LicencesFragment()
    }
}
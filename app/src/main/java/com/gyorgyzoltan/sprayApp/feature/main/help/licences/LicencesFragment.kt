package com.gyorgyzoltan.sprayApp.feature.main.help.licences

import androidx.lifecycle.viewModelScope
import com.gyorgyzoltan.sprayApp.R
import com.gyorgyzoltan.sprayApp.feature.main.help.licences.list.LicencesAdapter
import com.gyorgyzoltan.sprayApp.feature.main.help.licences.list.LicencesListItem
import com.gyorgyzoltan.sprayApp.feature.shared.ListFragment
import com.gyorgyzoltan.sprayApp.utils.openUrl
import org.koin.androidx.viewmodel.ext.android.viewModel

class LicencesFragment : ListFragment<LicencesViewModel, LicencesListItem>(R.string.licences_title) {

    override val viewModel by viewModel<LicencesViewModel>()

    override fun createAdapter() = LicencesAdapter(viewModel.viewModelScope, ::onLicenceSelected)

    private fun onLicenceSelected(url: String) = binding.recyclerView.openUrl(url)

    companion object {
        fun newInstance() = LicencesFragment()
    }
}
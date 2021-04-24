package com.gyorgyzoltan.sprayApp.help.licences

import androidx.lifecycle.viewModelScope
import com.gyorgyzoltan.sprayApp.help.licences.list.LicencesAdapter
import com.gyorgyzoltan.sprayApp.help.licences.list.LicencesListItem
import com.gyorgyzoltan.sprayApp.main.R
import com.gyorgyzoltan.sprayApp.main.shared.list.ListFragment
import com.gyorgyzoltan.sprayApp.main.shared.utilities.openUrl
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class LicencesFragment : ListFragment<LicencesViewModel, LicencesListItem>(R.string.licences_title) {

    override val viewModel by viewModel<LicencesViewModel>()

    override fun createAdapter() = LicencesAdapter(viewModel.viewModelScope, ::onLicenceSelected)

    private fun onLicenceSelected(url: String) = context?.openUrl(url)

    companion object {
        fun newInstance() = LicencesFragment()
    }
}
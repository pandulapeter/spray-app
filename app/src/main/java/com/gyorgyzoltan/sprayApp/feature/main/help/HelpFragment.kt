package com.gyorgyzoltan.sprayApp.feature.main.help

import androidx.lifecycle.viewModelScope
import com.gyorgyzoltan.sprayApp.R
import com.gyorgyzoltan.sprayApp.feature.SprayAppActivity
import com.gyorgyzoltan.sprayApp.feature.main.help.list.ClickableItemViewHolder
import com.gyorgyzoltan.sprayApp.feature.main.help.list.HelpAdapter
import com.gyorgyzoltan.sprayApp.feature.main.help.list.HelpListItem
import com.gyorgyzoltan.sprayApp.feature.shared.ListFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HelpFragment : ListFragment<HelpViewModel, HelpListItem>(R.string.main_help) {

    override val viewModel by viewModel<HelpViewModel>()

    override fun createAdapter() = HelpAdapter(
        scope = viewModel.viewModelScope,
        onItemClicked = ::onItemClicked
    )

    private fun onItemClicked(uiModel: ClickableItemViewHolder.UiModel) {
        when (uiModel.textResourceId) {
            R.string.help_show_tutorial -> (activity as? SprayAppActivity?)?.navigateToTutorial(false)
        }
    }

    companion object {
        fun newInstance() = HelpFragment()
    }
}
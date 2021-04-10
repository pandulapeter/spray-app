package com.gyorgyzoltan.sprayApp.presentation.feature.main.help

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gyorgyzoltan.sprayApp.presentation.R
import com.gyorgyzoltan.sprayApp.presentation.feature.main.help.list.ClickableItemViewHolder
import com.gyorgyzoltan.sprayApp.presentation.feature.main.help.list.HelpListItem
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.ListViewModel

internal class HelpViewModel : ListViewModel<HelpListItem>() {

    override val items: LiveData<List<HelpListItem>> = MutableLiveData(
        listOf(
            ClickableItemViewHolder.UiModel(R.string.help_show_tutorial, R.drawable.ic_help),
            ClickableItemViewHolder.UiModel(R.string.help_check_for_updates, R.drawable.ic_update),
            ClickableItemViewHolder.UiModel(R.string.help_call_customer_support, R.drawable.ic_phone),
            ClickableItemViewHolder.UiModel(R.string.help_contact_us, R.drawable.ic_mail),
            ClickableItemViewHolder.UiModel(R.string.help_share, R.drawable.ic_share),
            ClickableItemViewHolder.UiModel(R.string.help_open_source_licences, R.drawable.ic_licences)
        )
    )
}
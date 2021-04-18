package com.gyorgyzoltan.sprayApp.help.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gyorgyzoltan.sprayApp.help.R
import com.gyorgyzoltan.sprayApp.help.overview.list.HelpClickableItemViewHolder
import com.gyorgyzoltan.sprayApp.help.overview.list.HelpListItem
import com.gyorgyzoltan.sprayApp.main.shared.list.ListViewModel

internal class HelpViewModel : ListViewModel<HelpListItem>() {

    override val items: LiveData<List<HelpListItem>> = MutableLiveData(
        listOf(
            HelpClickableItemViewHolder.UiModel(R.string.help_show_tutorial, R.drawable.ic_help),
            HelpClickableItemViewHolder.UiModel(R.string.help_check_for_updates, R.drawable.ic_update),
            HelpClickableItemViewHolder.UiModel(R.string.help_call_customer_support, R.drawable.ic_phone),
            HelpClickableItemViewHolder.UiModel(R.string.help_contact_us, R.drawable.ic_mail),
            HelpClickableItemViewHolder.UiModel(R.string.help_share, R.drawable.ic_share),
            HelpClickableItemViewHolder.UiModel(R.string.help_open_source_licences, R.drawable.ic_licences)
        )
    )
}
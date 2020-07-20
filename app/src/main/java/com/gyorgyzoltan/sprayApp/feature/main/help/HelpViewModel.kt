package com.gyorgyzoltan.sprayApp.feature.main.help

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gyorgyzoltan.sprayApp.R
import com.gyorgyzoltan.sprayApp.feature.main.help.list.ClickableItemViewHolder
import com.gyorgyzoltan.sprayApp.feature.main.help.list.HelpListItem
import com.gyorgyzoltan.sprayApp.feature.shared.ListViewModel

class HelpViewModel : ListViewModel<HelpListItem>() {

    override val items: LiveData<List<HelpListItem>> = MutableLiveData(
        listOf(
            ClickableItemViewHolder.UiModel(R.string.help_show_tutorial, R.drawable.ic_help),
            ClickableItemViewHolder.UiModel(R.string.help_call_customer_support, R.drawable.ic_phone),
            ClickableItemViewHolder.UiModel(R.string.help_contact_us, R.drawable.ic_mail)
        )
    )
}
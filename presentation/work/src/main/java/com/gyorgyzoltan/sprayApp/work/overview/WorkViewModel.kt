package com.gyorgyzoltan.sprayApp.work.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gyorgyzoltan.sprayApp.main.R
import com.gyorgyzoltan.sprayApp.main.shared.list.ListViewModel
import com.gyorgyzoltan.sprayApp.work.overview.list.WorkListItem
import com.gyorgyzoltan.sprayApp.work.overview.list.WorkTextViewHolder

internal class WorkViewModel : ListViewModel<WorkListItem>() {

    override val items: LiveData<List<WorkListItem>> = MutableLiveData(
        listOf(
            WorkTextViewHolder.UiModel(R.string.work_placeholder)
        )
    )
}
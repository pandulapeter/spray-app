package com.gyorgyzoltan.sprayApp.presentation.feature.shared

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.list.ListItem

abstract class ListViewModel<LI : ListItem> : ViewModel() {

    abstract val items: LiveData<List<LI>>
}
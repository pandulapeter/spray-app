package com.gyorgyzoltan.sprayApp.presentation.feature.shared

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gyorgyzoltan.sprayApp.presentation.feature.shared.list.ListItem

abstract class ListViewModel<LI : ListItem>(val supportsRefresh: Boolean = false) : ViewModel() {

    abstract val items: LiveData<List<LI>>
    open val shouldShowLoadingIndicator: LiveData<Boolean> = MutableLiveData(false)
    open val shouldShowErrorView: LiveData<Boolean> = MutableLiveData(false)

    open fun loadData(isForceRefresh: Boolean) = Unit
}
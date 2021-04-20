package com.gyorgyzoltan.sprayApp.model.dependency

import androidx.annotation.StringRes
import com.gyorgyzoltan.sprayApp.model.R

enum class DependencyType(@StringRes val titleResourceId: Int) {
    APACHE_V2(R.string.licences_apache)
}
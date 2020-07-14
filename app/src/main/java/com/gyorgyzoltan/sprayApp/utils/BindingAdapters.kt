package com.gyorgyzoltan.sprayApp.utils

import android.view.View
import androidx.databinding.BindingAdapter

@set:BindingAdapter("android:visibility")
var View.visible
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }
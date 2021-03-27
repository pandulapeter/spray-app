package com.gyorgyzoltan.sprayApp.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@set:BindingAdapter("android:visibility")
var View.visible
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }

var View.notInvisible
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.INVISIBLE
    }

@BindingAdapter("url")
fun ImageView.loadUrl(url: String?) = load(url)

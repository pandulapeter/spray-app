package com.gyorgyzoltan.sprayApp.presentation.utils

internal fun consume(callback: () -> Unit) = true.also { callback() }
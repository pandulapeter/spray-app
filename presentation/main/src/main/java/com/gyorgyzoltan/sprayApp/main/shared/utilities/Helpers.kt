package com.gyorgyzoltan.sprayApp.main.shared.utilities

fun consume(callback: () -> Unit) = true.also { callback() }
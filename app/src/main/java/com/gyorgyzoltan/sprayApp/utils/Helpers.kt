package com.gyorgyzoltan.sprayApp.utils

fun consume(callback: () -> Unit) = true.also { callback() }
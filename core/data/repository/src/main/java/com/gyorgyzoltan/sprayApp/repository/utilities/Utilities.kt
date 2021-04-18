package com.gyorgyzoltan.sprayApp.repository.utilities

import com.gyorgyzoltan.sprayApp.debugMenu.DebugMenu
import com.gyorgyzoltan.sprayApp.model.DataState

@Suppress("TooGenericExceptionCaught")
suspend fun <T> T?.toDataState(function: suspend () -> T): DataState<T> = try {
    DataState.Idle(function())
} catch (exception: Exception) {
    DebugMenu.log("Error: ${exception.message}")
    DataState.Error(this, exception)
}

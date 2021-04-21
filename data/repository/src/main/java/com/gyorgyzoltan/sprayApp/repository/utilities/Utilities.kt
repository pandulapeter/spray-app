package com.gyorgyzoltan.sprayApp.repository.utilities

import com.gyorgyzoltan.sprayApp.model.shared.DataState

@Suppress("TooGenericExceptionCaught")
internal suspend fun <T> T?.toDataState(function: suspend () -> T): DataState<T> = try {
    DataState.Idle(function())
} catch (exception: Exception) {
    DataState.Error(this, exception)
}

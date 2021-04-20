package com.gyorgyzoltan.sprayApp.model.shared

sealed class DataState<T> {

    abstract val data: T?

    data class Idle<T>(override val data: T) : DataState<T>()

    data class Loading<T>(override val data: T?) : DataState<T>()

    data class Error<T>(override val data: T?, val exception: Throwable) : DataState<T>()
}
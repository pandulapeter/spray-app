package com.gyorgyzoltan.sprayApp.main.shared.utilities

import android.os.Bundle
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

sealed class BundleArgumentDelegate<T>(protected val key: String, protected val defaultValue: T) : ReadWriteProperty<Bundle?, T> {

    class Boolean(key: String, defaultValue: kotlin.Boolean = false) : BundleArgumentDelegate<kotlin.Boolean>(key, defaultValue) {

        override fun getValue(thisRef: Bundle?, property: KProperty<*>) = thisRef?.getBoolean(key, defaultValue) ?: defaultValue

        override fun setValue(thisRef: Bundle?, property: KProperty<*>, value: kotlin.Boolean) = thisRef?.putBoolean(key, value) ?: Unit
    }

    class Float(key: String, defaultValue: kotlin.Float = -1f) : BundleArgumentDelegate<kotlin.Float>(key, defaultValue) {

        override fun getValue(thisRef: Bundle?, property: KProperty<*>) = thisRef?.getFloat(key, defaultValue) ?: defaultValue

        override fun setValue(thisRef: Bundle?, property: KProperty<*>, value: kotlin.Float) = thisRef?.putFloat(key, value) ?: Unit
    }
}
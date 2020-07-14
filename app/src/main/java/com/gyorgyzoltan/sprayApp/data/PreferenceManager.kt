package com.gyorgyzoltan.sprayApp.data

import android.content.Context
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PreferenceManager(context: Context) {

    private val preferences = context.applicationContext.getSharedPreferences("preferences", Context.MODE_PRIVATE)
    var hasSeenTutorial by MutablePreferenceFieldDelegate.Boolean("hasSeenTutorial")

    private sealed class MutablePreferenceFieldDelegate<T>(protected val key: String) : ReadWriteProperty<PreferenceManager, T> {

        class Boolean(key: String) : MutablePreferenceFieldDelegate<kotlin.Boolean>(key) {

            override fun getValue(thisRef: PreferenceManager, property: KProperty<*>) = thisRef.preferences.getBoolean(key, false)

            override fun setValue(thisRef: PreferenceManager, property: KProperty<*>, value: kotlin.Boolean) = thisRef.preferences.edit().putBoolean(key, value).apply()
        }
    }
}
package com.gyorgyzoltan.sprayApp.data

import android.content.Context
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PreferenceManager(context: Context) {

    private val preferences = context.applicationContext.getSharedPreferences("preferences", Context.MODE_PRIVATE)
    var hasSeenTutorial by MutablePreferenceFieldDelegate.Boolean("hasSeenTutorial")
    var isConfigurationSet by MutablePreferenceFieldDelegate.Boolean("isConfigurationSet")
    var selectedNozzleName by MutablePreferenceFieldDelegate.String("selectedNozzleName")

    private sealed class MutablePreferenceFieldDelegate<T>(protected val key: kotlin.String) : ReadWriteProperty<PreferenceManager, T> {

        class Boolean(key: kotlin.String) : MutablePreferenceFieldDelegate<kotlin.Boolean>(key) {

            override fun getValue(thisRef: PreferenceManager, property: KProperty<*>) = thisRef.preferences.getBoolean(key, false)

            override fun setValue(thisRef: PreferenceManager, property: KProperty<*>, value: kotlin.Boolean) = thisRef.preferences.edit().putBoolean(key, value).apply()
        }

        class String(key: kotlin.String) : MutablePreferenceFieldDelegate<kotlin.String>(key) {

            override fun getValue(thisRef: PreferenceManager, property: KProperty<*>) = thisRef.preferences.getString(key, "").orEmpty()

            override fun setValue(thisRef: PreferenceManager, property: KProperty<*>, value: kotlin.String) = thisRef.preferences.edit().putString(key, value).apply()
        }
    }
}
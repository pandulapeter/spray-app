package com.gyorgyzoltan.sprayApp.local.implementation.preferences

import android.content.Context
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal class PreferenceManagerImpl(context: Context) : PreferenceManager {

    private val preferences = context.applicationContext.getSharedPreferences("preferences", Context.MODE_PRIVATE)
    override var hasSeenTutorial by MutablePreferenceFieldDelegate.Boolean("hasSeenTutorial")
    override var selectedNozzleName by MutablePreferenceFieldDelegate.String("selectedNozzleName")

    private sealed class MutablePreferenceFieldDelegate<T>(protected val key: kotlin.String) : ReadWriteProperty<PreferenceManagerImpl, T> {

        class Boolean(key: kotlin.String) : MutablePreferenceFieldDelegate<kotlin.Boolean>(key) {

            override fun getValue(thisRef: PreferenceManagerImpl, property: KProperty<*>) = thisRef.preferences.getBoolean(key, false)

            override fun setValue(thisRef: PreferenceManagerImpl, property: KProperty<*>, value: kotlin.Boolean) = thisRef.preferences.edit().putBoolean(key, value).apply()
        }

        class String(key: kotlin.String) : MutablePreferenceFieldDelegate<kotlin.String>(key) {

            override fun getValue(thisRef: PreferenceManagerImpl, property: KProperty<*>) = thisRef.preferences.getString(key, "").orEmpty()

            override fun setValue(thisRef: PreferenceManagerImpl, property: KProperty<*>, value: kotlin.String) = thisRef.preferences.edit().putString(key, value).apply()
        }
    }
}
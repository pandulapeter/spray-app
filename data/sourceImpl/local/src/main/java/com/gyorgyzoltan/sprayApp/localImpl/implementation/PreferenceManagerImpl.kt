package com.gyorgyzoltan.sprayApp.localImpl.implementation

import android.content.Context
import com.gyorgyzoltan.sprayApp.local.PreferenceManager
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal class PreferenceManagerImpl(context: Context) : PreferenceManager {

    private val preferences = context.applicationContext.getSharedPreferences("preferences", Context.MODE_PRIVATE)
    override var hasSeenTutorial by MutablePreferenceFieldDelegate.Boolean("hasSeenTutorial")
    override var nozzleName by MutablePreferenceFieldDelegate.String("nozzleName")
    override val isNozzleNameSet get() = nozzleName != DEFAULT_STRING
    override var wheelRadius by MutablePreferenceFieldDelegate.Float("wheelRadius")
    override val isWheelRadiusSet get() = wheelRadius != DEFAULT_FLOAT
    override var screwCount by MutablePreferenceFieldDelegate.Int("screwCount")
    override val isScrewCountSet get() = screwCount != DEFAULT_INT
    override var nozzleCount by MutablePreferenceFieldDelegate.Int("nozzleCount")
    override val isNozzleCountSet get() = nozzleCount != DEFAULT_INT
    override var nozzleDistance by MutablePreferenceFieldDelegate.Float("nozzleDistance")
    override val isNozzleDistanceSet get() = nozzleDistance != DEFAULT_FLOAT

    private sealed class MutablePreferenceFieldDelegate<T>(protected val key: kotlin.String) : ReadWriteProperty<PreferenceManagerImpl, T> {

        class Boolean(key: kotlin.String) : MutablePreferenceFieldDelegate<kotlin.Boolean>(key) {

            override fun getValue(thisRef: PreferenceManagerImpl, property: KProperty<*>) = thisRef.preferences.getBoolean(key, false)

            override fun setValue(thisRef: PreferenceManagerImpl, property: KProperty<*>, value: kotlin.Boolean) = thisRef.preferences.edit().putBoolean(key, value).apply()
        }

        class Int(key: kotlin.String) : MutablePreferenceFieldDelegate<kotlin.Int>(key) {

            override fun getValue(thisRef: PreferenceManagerImpl, property: KProperty<*>) = thisRef.preferences.getInt(key, DEFAULT_INT)

            override fun setValue(thisRef: PreferenceManagerImpl, property: KProperty<*>, value: kotlin.Int) = thisRef.preferences.edit().putInt(key, value).apply()
        }

        class Float(key: kotlin.String) : MutablePreferenceFieldDelegate<kotlin.Float>(key) {

            override fun getValue(thisRef: PreferenceManagerImpl, property: KProperty<*>) = thisRef.preferences.getFloat(key, DEFAULT_FLOAT)

            override fun setValue(thisRef: PreferenceManagerImpl, property: KProperty<*>, value: kotlin.Float) = thisRef.preferences.edit().putFloat(key, value).apply()
        }

        class String(key: kotlin.String) : MutablePreferenceFieldDelegate<kotlin.String>(key) {

            override fun getValue(thisRef: PreferenceManagerImpl, property: KProperty<*>) = thisRef.preferences.getString(key, DEFAULT_STRING).orEmpty()

            override fun setValue(thisRef: PreferenceManagerImpl, property: KProperty<*>, value: kotlin.String) = thisRef.preferences.edit().putString(key, value).apply()
        }
    }

    companion object {
        private const val DEFAULT_STRING = ""
        private const val DEFAULT_FLOAT = -1f
        private const val DEFAULT_INT = -1
    }
}
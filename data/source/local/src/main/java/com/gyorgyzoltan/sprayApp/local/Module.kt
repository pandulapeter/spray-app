package com.gyorgyzoltan.sprayApp.local

import com.gyorgyzoltan.sprayApp.local.implementation.preferences.PreferenceManager
import com.gyorgyzoltan.sprayApp.local.implementation.preferences.PreferenceManagerImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localSourceModule = module {

    // Preferences
    single<PreferenceManager> { PreferenceManagerImpl(androidContext()) }
}
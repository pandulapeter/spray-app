package com.gyorgyzoltan.sprayApp.local

import com.gyorgyzoltan.sprayApp.local.implementation.preferences.PreferenceManager
import com.gyorgyzoltan.sprayApp.local.implementation.preferences.PreferenceManagerImpl
import com.gyorgyzoltan.sprayApp.local.localSource.nozzle.NozzleLocalSource
import com.gyorgyzoltan.sprayApp.local.localSource.nozzle.NozzleLocalSourceImpl
import com.gyorgyzoltan.sprayApp.local.localSource.nozzleType.NozzleTypeLocalSource
import com.gyorgyzoltan.sprayApp.local.localSource.nozzleType.NozzleTypeLocalSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localSourceModule = module {

    // Preferences
    single<PreferenceManager> { PreferenceManagerImpl(androidContext()) }

    // Local sources
    factory<NozzleLocalSource> { NozzleLocalSourceImpl() }
    factory<NozzleTypeLocalSource> { NozzleTypeLocalSourceImpl() }
}
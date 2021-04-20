package com.gyorgyzoltan.sprayApp.local

import androidx.room.Room
import com.gyorgyzoltan.sprayApp.local.implementation.database.DatabaseManager
import com.gyorgyzoltan.sprayApp.local.implementation.preferences.PreferenceManager
import com.gyorgyzoltan.sprayApp.local.implementation.preferences.PreferenceManagerImpl
import com.gyorgyzoltan.sprayApp.local.localSource.nozzle.NozzleLocalSource
import com.gyorgyzoltan.sprayApp.local.localSource.nozzle.NozzleLocalSourceImpl
import com.gyorgyzoltan.sprayApp.local.localSource.nozzleType.NozzleTypeLocalSource
import com.gyorgyzoltan.sprayApp.local.localSource.nozzleType.NozzleTypeLocalSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localSourceModule = module {

    // Database
    single { Room.databaseBuilder(androidContext(), DatabaseManager::class.java, "nozzleDatabase.db").build() }

    // Preferences
    single<PreferenceManager> { PreferenceManagerImpl(androidContext()) }

    // Local sources
    factory<NozzleLocalSource> { NozzleLocalSourceImpl(get()) }
    factory<NozzleTypeLocalSource> { NozzleTypeLocalSourceImpl(get()) }
}
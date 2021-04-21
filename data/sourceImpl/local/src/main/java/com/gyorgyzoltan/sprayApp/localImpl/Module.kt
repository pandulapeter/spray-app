package com.gyorgyzoltan.sprayApp.localImpl

import androidx.room.Room
import com.gyorgyzoltan.sprayApp.local.DependencyLocalSource
import com.gyorgyzoltan.sprayApp.local.NozzleColorLocalSource
import com.gyorgyzoltan.sprayApp.local.NozzleStubLocalSource
import com.gyorgyzoltan.sprayApp.local.NozzleTypeLocalSource
import com.gyorgyzoltan.sprayApp.local.PreferenceManager
import com.gyorgyzoltan.sprayApp.localImpl.implementation.DatabaseManager
import com.gyorgyzoltan.sprayApp.localImpl.implementation.PreferenceManagerImpl
import com.gyorgyzoltan.sprayApp.localImpl.implementation.dao.DependencyDao
import com.gyorgyzoltan.sprayApp.localImpl.implementation.dao.NozzleColorDao
import com.gyorgyzoltan.sprayApp.localImpl.localSource.DependencyLocalSourceImpl
import com.gyorgyzoltan.sprayApp.localImpl.localSource.NozzleColorLocalSourceImpl
import com.gyorgyzoltan.sprayApp.localImpl.localSource.NozzleStubLocalSourceImpl
import com.gyorgyzoltan.sprayApp.localImpl.localSource.NozzleTypeLocalSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localSourceModule = module {

    // Database
    single { Room.databaseBuilder(androidContext(), DatabaseManager::class.java, "nozzleDatabase.db").build() }
    single { DependencyDao() }
    single { NozzleColorDao() }

    // Preferences
    single<PreferenceManager> { PreferenceManagerImpl(androidContext()) }

    // Local sources
    factory<DependencyLocalSource> { DependencyLocalSourceImpl(get()) }
    factory<NozzleColorLocalSource> { NozzleColorLocalSourceImpl(get()) }
    factory<NozzleStubLocalSource> { NozzleStubLocalSourceImpl(get<DatabaseManager>().getNozzleDao()) }
    factory<NozzleTypeLocalSource> { NozzleTypeLocalSourceImpl(get<DatabaseManager>().getNozzleTypeDao()) }
}
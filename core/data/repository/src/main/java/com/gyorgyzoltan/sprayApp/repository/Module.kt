package com.gyorgyzoltan.sprayApp.repository

import com.gyorgyzoltan.sprayApp.repository.preferences.PreferenceManager
import com.gyorgyzoltan.sprayApp.repository.preferences.PreferenceManagerImpl
import com.gyorgyzoltan.sprayApp.repository.repository.nozzle.NozzleRepository
import com.gyorgyzoltan.sprayApp.repository.repository.nozzle.NozzleRepositoryImpl
import com.gyorgyzoltan.sprayApp.repository.repository.nozzleType.NozzleTypeRepository
import com.gyorgyzoltan.sprayApp.repository.repository.nozzleType.NozzleTypeRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {

    // Preferences
    single<PreferenceManager> { PreferenceManagerImpl(androidContext()) }

    // Repositories
    single<NozzleRepository> { NozzleRepositoryImpl(get(), get()) }
    single<NozzleTypeRepository> { NozzleTypeRepositoryImpl(get()) }
}


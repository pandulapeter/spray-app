package com.gyorgyzoltan.sprayApp.repository

import com.gyorgyzoltan.sprayApp.repository.networking.NetworkingManager
import com.gyorgyzoltan.sprayApp.repository.networking.NetworkingManagerImpl
import com.gyorgyzoltan.sprayApp.repository.preferences.PreferenceManager
import com.gyorgyzoltan.sprayApp.repository.preferences.PreferenceManagerImpl
import com.gyorgyzoltan.sprayApp.repository.repository.nozzle.NozzleRepository
import com.gyorgyzoltan.sprayApp.repository.repository.nozzle.NozzleRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {

    // Networking
    single<NetworkingManager> { NetworkingManagerImpl() }

    // Preferences
    single<PreferenceManager> { PreferenceManagerImpl(androidContext()) }

    // Repositories
    single<NozzleRepository> { NozzleRepositoryImpl(get()) }
}


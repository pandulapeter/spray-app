package com.gyorgyzoltan.sprayApp.remote

import com.gyorgyzoltan.sprayApp.remote.implementation.NetworkingManager
import com.gyorgyzoltan.sprayApp.remote.implementation.NetworkingManagerImpl
import com.gyorgyzoltan.sprayApp.remote.remoteSource.nozzle.NozzleRemoteSource
import com.gyorgyzoltan.sprayApp.remote.remoteSource.nozzle.NozzleRemoteSourceImpl
import com.gyorgyzoltan.sprayApp.remote.remoteSource.nozzleType.NozzleTypeRemoteSource
import com.gyorgyzoltan.sprayApp.remote.remoteSource.nozzleType.NozzleTypeRemoteSourceImpl
import org.koin.dsl.module

val remoteSourceModule = module {

    // Implementation
    single<NetworkingManager> { NetworkingManagerImpl() }

    // Remote sources
    factory<NozzleRemoteSource> { NozzleRemoteSourceImpl(get()) }
    factory<NozzleTypeRemoteSource> { NozzleTypeRemoteSourceImpl(get()) }
}
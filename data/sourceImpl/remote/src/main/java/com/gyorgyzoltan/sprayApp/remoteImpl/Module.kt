package com.gyorgyzoltan.sprayApp.remoteImpl

import com.gyorgyzoltan.sprayApp.remote.NozzleStubRemoteSource
import com.gyorgyzoltan.sprayApp.remote.NozzleTypeRemoteSource
import com.gyorgyzoltan.sprayApp.remoteImpl.implementation.NetworkingManager
import com.gyorgyzoltan.sprayApp.remoteImpl.implementation.NetworkingManagerImpl
import com.gyorgyzoltan.sprayApp.remoteImpl.remoteSource.NozzleStubRemoteSourceImpl
import com.gyorgyzoltan.sprayApp.remoteImpl.remoteSource.NozzleTypeRemoteSourceImpl
import org.koin.dsl.module

val remoteSourceModule = module {

    // Implementation
    single<NetworkingManager> { NetworkingManagerImpl() }

    // Remote sources
    factory<NozzleStubRemoteSource> { NozzleStubRemoteSourceImpl(get()) }
    factory<NozzleTypeRemoteSource> { NozzleTypeRemoteSourceImpl(get()) }
}
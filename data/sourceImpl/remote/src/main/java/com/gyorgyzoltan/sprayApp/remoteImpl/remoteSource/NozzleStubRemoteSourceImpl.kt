package com.gyorgyzoltan.sprayApp.remoteImpl.remoteSource

import com.gyorgyzoltan.sprayApp.remote.NozzleStubRemoteSource
import com.gyorgyzoltan.sprayApp.remoteImpl.implementation.NetworkingManager

internal class NozzleStubRemoteSourceImpl(private val networkingManager: NetworkingManager) : NozzleStubRemoteSource {

    override suspend fun getNozzleStubs() = networkingManager.service.getNozzleStubs().mapNotNull { it.toNozzleStubImpl() }
}
package com.gyorgyzoltan.sprayApp.remote.remoteSource.nozzleType

import com.gyorgyzoltan.sprayApp.remote.implementation.NetworkingManager

internal class NozzleTypeRemoteSourceImpl(private val networkingManager: NetworkingManager) : NozzleTypeRemoteSource {

    override suspend fun getNozzleTypes() = networkingManager.service.getNozzleTypes()
}
package com.gyorgyzoltan.sprayApp.remote.remoteSource.nozzle

import com.gyorgyzoltan.sprayApp.remote.implementation.NetworkingManager

internal class NozzleRemoteSourceImpl(private val networkingManager: NetworkingManager) : NozzleRemoteSource {

    override suspend fun getNozzles() = networkingManager.service.getNozzles()
}
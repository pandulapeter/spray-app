package com.gyorgyzoltan.sprayApp.remoteImpl.remoteSource

import com.gyorgyzoltan.sprayApp.remote.NozzleTypeRemoteSource
import com.gyorgyzoltan.sprayApp.remoteImpl.implementation.NetworkingManager

internal class NozzleTypeRemoteSourceImpl(private val networkingManager: NetworkingManager) : NozzleTypeRemoteSource {

    override suspend fun getNozzleTypes() = networkingManager.service.getNozzleTypes().mapNotNull { it.toNozzleTypeImpl() }
}
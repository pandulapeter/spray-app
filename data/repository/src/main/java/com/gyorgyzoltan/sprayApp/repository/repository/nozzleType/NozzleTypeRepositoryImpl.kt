package com.gyorgyzoltan.sprayApp.repository.repository.nozzleType

import com.gyorgyzoltan.sprayApp.local.localSource.nozzleType.NozzleTypeLocalSource
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleType
import com.gyorgyzoltan.sprayApp.remote.remoteSource.nozzleType.NozzleTypeRemoteSource
import com.gyorgyzoltan.sprayApp.repository.mapper.toNozzleType

internal class NozzleTypeRepositoryImpl(
    private val nozzleTypeLocalSource: NozzleTypeLocalSource,
    private val nozzleTypeRemoteSource: NozzleTypeRemoteSource
) : NozzleTypeRepository {

    private var cache: List<NozzleType>? = null

    override suspend fun getNozzleTypes(isForceRefresh: Boolean) = cache.let { oldCache ->
        if (isForceRefresh || oldCache == null) {
            loadNozzleTypesFromRemote().also { cache = it }
        } else {
            oldCache
        }
    }

    private suspend fun loadNozzleTypesFromLocal() = nozzleTypeLocalSource.getNozzleTypes().mapNotNull { it.toNozzleType() }

    private suspend fun loadNozzleTypesFromRemote() = nozzleTypeRemoteSource.getNozzleTypes().mapNotNull { it.toNozzleType() }

}
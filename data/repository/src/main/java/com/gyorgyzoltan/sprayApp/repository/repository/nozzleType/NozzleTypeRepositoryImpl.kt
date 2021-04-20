package com.gyorgyzoltan.sprayApp.repository.repository.nozzleType

import com.gyorgyzoltan.sprayApp.local.localSource.nozzleType.NozzleTypeLocalSource
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleType
import com.gyorgyzoltan.sprayApp.remote.remoteSource.nozzleType.NozzleTypeRemoteSource
import com.gyorgyzoltan.sprayApp.repository.mapper.toEntity
import com.gyorgyzoltan.sprayApp.repository.mapper.toNozzleType

internal class NozzleTypeRepositoryImpl(
    private val nozzleTypeLocalSource: NozzleTypeLocalSource,
    private val nozzleTypeRemoteSource: NozzleTypeRemoteSource
) : NozzleTypeRepository {

    private var cache: List<NozzleType>? = null

    override suspend fun getNozzleTypes(isForceRefresh: Boolean): List<NozzleType> {
        if (cache.isNullOrEmpty()) {
            cache = loadNozzleTypesFromLocal()
        }
        return cache.let { oldCache ->
            if (isForceRefresh || oldCache.isNullOrEmpty()) {
                loadNozzleTypesFromRemote().also { cache = it }
            } else {
                oldCache
            }
        }
    }

    private suspend fun loadNozzleTypesFromLocal() = nozzleTypeLocalSource.getNozzleTypes().mapNotNull { it.toNozzleType() }

    private suspend fun loadNozzleTypesFromRemote() = nozzleTypeRemoteSource.getNozzleTypes().mapNotNull { it.toNozzleType() }.also { nozzleTypes ->
        nozzleTypeLocalSource.saveNozzleTypes(nozzleTypes.map { it.toEntity() })
    }

}
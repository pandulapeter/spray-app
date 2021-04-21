package com.gyorgyzoltan.sprayApp.repository.repository.nozzleType

import com.gyorgyzoltan.sprayApp.local.NozzleTypeLocalSource
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleType
import com.gyorgyzoltan.sprayApp.remote.NozzleTypeRemoteSource

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

    private suspend fun loadNozzleTypesFromLocal() = nozzleTypeLocalSource.getNozzleTypes()

    private suspend fun loadNozzleTypesFromRemote() = nozzleTypeRemoteSource.getNozzleTypes().also { nozzleTypes ->
        nozzleTypeLocalSource.saveNozzleTypes(nozzleTypes)
    }
}
package com.gyorgyzoltan.sprayApp.repository.repository.nozzle

import com.gyorgyzoltan.sprayApp.local.localSource.nozzle.NozzleLocalSource
import com.gyorgyzoltan.sprayApp.model.nozzle.Nozzle
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleColor
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleType
import com.gyorgyzoltan.sprayApp.model.shared.DataState
import com.gyorgyzoltan.sprayApp.model.shared.RepositoryNotInitializedException
import com.gyorgyzoltan.sprayApp.remote.remoteSource.nozzle.NozzleRemoteSource
import com.gyorgyzoltan.sprayApp.repository.mapper.toEntity
import com.gyorgyzoltan.sprayApp.repository.mapper.toNozzle
import com.gyorgyzoltan.sprayApp.repository.repository.nozzleType.NozzleTypeRepository
import com.gyorgyzoltan.sprayApp.repository.utilities.toDataState
import kotlinx.coroutines.flow.MutableStateFlow

internal class NozzleRepositoryImpl(
    private val nozzleLocalSource: NozzleLocalSource,
    private val nozzleRemoteSource: NozzleRemoteSource,
    private val nozzleTypeRepository: NozzleTypeRepository
) : NozzleRepository {

    override val nozzles = MutableStateFlow<DataState<List<Nozzle>>>(DataState.Error(null, RepositoryNotInitializedException()))

    override suspend fun refresh(isForceRefresh: Boolean) {
        if (nozzles.value !is DataState.Loading) {
            val initialCache = nozzles.value.data
            var cache = nozzles.value.data
            if (cache.isNullOrEmpty()) {
                nozzles.value = DataState.Loading(cache)
                cache = loadNozzlesFromLocal(getNozzleTypes(false))
            }
            if (isForceRefresh || initialCache.isNullOrEmpty()) {
                nozzles.value = DataState.Loading(cache)
                nozzles.value = cache.toDataState { loadNozzlesFromRemote(getNozzleTypes(isForceRefresh)) }
            } else {
                nozzles.value = DataState.Idle(cache)
            }
        }
    }

    private suspend fun getNozzleTypes(isForceRefresh: Boolean) = try {
        nozzleTypeRepository.getNozzleTypes(isForceRefresh)
    } catch (_: Exception) {
        emptyList()
    }

    private suspend fun loadNozzlesFromLocal(nozzleTypes: List<NozzleType>) = NozzleColor.values().toList().let { nozzleColors ->
        nozzleLocalSource.getNozzles().mapNotNull {
            it.toNozzle(
                nozzleTypes = nozzleTypes,
                nozzleColors = nozzleColors
            )
        }
    }

    private suspend fun loadNozzlesFromRemote(nozzleTypes: List<NozzleType>) = NozzleColor.values().toList().let { nozzleColors ->
        nozzleRemoteSource.getNozzles().mapNotNull {
            it.toNozzle(
                nozzleTypes = nozzleTypes,
                nozzleColors = nozzleColors
            )
        }.also { nozzles ->
            nozzleLocalSource.saveNozzles(nozzles.map { it.toEntity() })
        }
    }
}
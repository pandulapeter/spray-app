package com.gyorgyzoltan.sprayApp.repository.repository.nozzle

import com.gyorgyzoltan.sprayApp.local.NozzleColorLocalSource
import com.gyorgyzoltan.sprayApp.local.NozzleStubLocalSource
import com.gyorgyzoltan.sprayApp.model.nozzle.Nozzle
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleType
import com.gyorgyzoltan.sprayApp.model.shared.DataState
import com.gyorgyzoltan.sprayApp.model.shared.RepositoryNotInitializedException
import com.gyorgyzoltan.sprayApp.remote.NozzleStubRemoteSource
import com.gyorgyzoltan.sprayApp.repository.repository.nozzleType.NozzleTypeRepository
import com.gyorgyzoltan.sprayApp.repository.utilities.toDataState
import com.gyorgyzoltan.sprayApp.repository.utilities.toNozzleImpl
import kotlinx.coroutines.flow.MutableStateFlow

internal class NozzleRepositoryImpl(
    private val nozzleColorLocalSource: NozzleColorLocalSource,
    private val nozzleStubLocalSource: NozzleStubLocalSource,
    private val nozzleStubRemoteSource: NozzleStubRemoteSource,
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

    private suspend fun loadNozzlesFromLocal(nozzleTypes: List<NozzleType>) = nozzleColorLocalSource.getNozzleColors().let { nozzleColors ->
        nozzleStubLocalSource.getNozzleStubs().mapNotNull {
            it.toNozzleImpl(
                nozzleTypes = nozzleTypes,
                nozzleColors = nozzleColors
            )
        }
    }

    private suspend fun loadNozzlesFromRemote(nozzleTypes: List<NozzleType>) = nozzleColorLocalSource.getNozzleColors().let { nozzleColors ->
        nozzleStubRemoteSource.getNozzleStubs().mapNotNull {
            it.toNozzleImpl(
                nozzleTypes = nozzleTypes,
                nozzleColors = nozzleColors
            )
        }.also { nozzles ->
            nozzleStubLocalSource.saveNozzleStubs(nozzles)
        }
    }
}
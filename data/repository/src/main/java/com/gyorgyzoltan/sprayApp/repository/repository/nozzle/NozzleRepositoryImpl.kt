package com.gyorgyzoltan.sprayApp.repository.repository.nozzle

import com.gyorgyzoltan.sprayApp.local.localSource.nozzle.NozzleLocalSource
import com.gyorgyzoltan.sprayApp.model.DataState
import com.gyorgyzoltan.sprayApp.model.nozzle.Nozzle
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleColor
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleType
import com.gyorgyzoltan.sprayApp.remote.remoteSource.nozzle.NozzleRemoteSource
import com.gyorgyzoltan.sprayApp.repository.mapper.toNozzle
import com.gyorgyzoltan.sprayApp.repository.repository.nozzleType.NozzleTypeRepository
import com.gyorgyzoltan.sprayApp.repository.utilities.toDataState
import kotlinx.coroutines.flow.MutableStateFlow

internal class NozzleRepositoryImpl(
    private val nozzleLocalSource: NozzleLocalSource,
    private val nozzleRemoteSource: NozzleRemoteSource,
    private val nozzleTypeRepository: NozzleTypeRepository
) : NozzleRepository {

    override val nozzles = MutableStateFlow<DataState<List<Nozzle>>>(DataState.Loading(null))

    override suspend fun refresh(isForceRefresh: Boolean) {
        if (isForceRefresh || nozzles.value.data == null) {
            nozzles.value = DataState.Loading(nozzles.value.data)
            nozzles.value = nozzles.value.data.toDataState { loadNozzlesFromRemote(getNozzleTypes(isForceRefresh)) }
        }
    }

    private suspend fun getNozzleTypes(isForceRefresh: Boolean) = nozzleTypeRepository.getNozzleTypes(isForceRefresh)

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
        }
    }
}
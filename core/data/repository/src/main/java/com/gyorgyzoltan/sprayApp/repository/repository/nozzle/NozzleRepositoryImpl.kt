package com.gyorgyzoltan.sprayApp.repository.repository.nozzle

import com.gyorgyzoltan.sprayApp.model.DataState
import com.gyorgyzoltan.sprayApp.model.nozzle.Nozzle
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleColor
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleType
import com.gyorgyzoltan.sprayApp.repository.mapper.toNozzle
import com.gyorgyzoltan.sprayApp.repository.networking.NetworkingManager
import com.gyorgyzoltan.sprayApp.repository.utilities.toDataState
import kotlinx.coroutines.flow.MutableStateFlow

internal class NozzleRepositoryImpl(private val networkingManager: NetworkingManager) : NozzleRepository {

    override val nozzles = MutableStateFlow<DataState<List<Nozzle>>>(DataState.Loading(null))

    override suspend fun refresh(isForceRefresh: Boolean, nozzleTypes: List<NozzleType>) {
        if (isForceRefresh || nozzles.value.data == null) {
            nozzles.value = DataState.Loading(nozzles.value.data)
            nozzles.value = nozzles.value.data.toDataState { loadNozzlesFromRemote(nozzleTypes) }
        }
    }

    private suspend fun loadNozzlesFromRemote(nozzleTypes: List<NozzleType>) = NozzleColor.values().toList().let { nozzleColors ->
        networkingManager.networkingService.getNozzles().mapNotNull {
            it.toNozzle(
                nozzleTypes = nozzleTypes,
                nozzleColors = nozzleColors
            )
        }
    }
}
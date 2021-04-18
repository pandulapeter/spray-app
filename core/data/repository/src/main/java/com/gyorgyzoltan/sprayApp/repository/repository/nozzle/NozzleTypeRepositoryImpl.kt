package com.gyorgyzoltan.sprayApp.repository.repository.nozzle

import com.gyorgyzoltan.sprayApp.model.DataState
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleType
import com.gyorgyzoltan.sprayApp.repository.mapper.toNozzleType
import com.gyorgyzoltan.sprayApp.repository.networking.NetworkingManager
import com.gyorgyzoltan.sprayApp.repository.utilities.toDataState
import kotlinx.coroutines.flow.MutableStateFlow

internal class NozzleTypeRepositoryImpl(private val networkingManager: NetworkingManager) : NozzleTypeRepository {

    override val nozzleTypes = MutableStateFlow<DataState<List<NozzleType>>>(DataState.Loading(null))

    override suspend fun refresh() {
        nozzleTypes.value = DataState.Loading(nozzleTypes.value.data)
        nozzleTypes.value = nozzleTypes.value.data.toDataState { networkingManager.networkingService.getNozzleTypes().mapNotNull { it.toNozzleType() } }
    }
}
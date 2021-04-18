package com.gyorgyzoltan.sprayApp.repository.repository.nozzle

import com.gyorgyzoltan.sprayApp.model.DataState
import com.gyorgyzoltan.sprayApp.model.nozzle.Nozzle
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleType
import kotlinx.coroutines.flow.StateFlow

interface NozzleRepository {

    val nozzles: StateFlow<DataState<List<Nozzle>>>

    suspend fun refresh(isForceRefresh: Boolean, nozzleTypes: List<NozzleType>)
}
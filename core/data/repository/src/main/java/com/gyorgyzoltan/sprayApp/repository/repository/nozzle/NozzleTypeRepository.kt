package com.gyorgyzoltan.sprayApp.repository.repository.nozzle

import com.gyorgyzoltan.sprayApp.model.DataState
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleType
import kotlinx.coroutines.flow.StateFlow

interface NozzleTypeRepository {

    val nozzleTypes: StateFlow<DataState<List<NozzleType>>>

    suspend fun refresh(isForceRefresh: Boolean)
}
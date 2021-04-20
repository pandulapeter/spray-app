package com.gyorgyzoltan.sprayApp.repository.repository.nozzle

import com.gyorgyzoltan.sprayApp.model.DataState
import com.gyorgyzoltan.sprayApp.model.nozzle.Nozzle
import kotlinx.coroutines.flow.StateFlow

interface NozzleRepository {

    val nozzles: StateFlow<DataState<List<Nozzle>>>

    suspend fun refresh(isForceRefresh: Boolean)
}
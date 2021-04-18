package com.gyorgyzoltan.sprayApp.repository.repository.nozzle

import com.gyorgyzoltan.sprayApp.model.DataState
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleType
import kotlinx.coroutines.flow.Flow

interface NozzleTypeRepository {

    val nozzleTypes: Flow<DataState<List<NozzleType>>>

    suspend fun refresh()
}
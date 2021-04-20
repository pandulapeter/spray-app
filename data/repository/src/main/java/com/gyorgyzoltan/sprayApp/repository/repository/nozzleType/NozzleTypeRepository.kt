package com.gyorgyzoltan.sprayApp.repository.repository.nozzleType

import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleType

interface NozzleTypeRepository {

    suspend fun getNozzleTypes(isForceRefresh: Boolean): List<NozzleType>
}
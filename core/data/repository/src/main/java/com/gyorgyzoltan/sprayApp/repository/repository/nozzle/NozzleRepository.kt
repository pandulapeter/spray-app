package com.gyorgyzoltan.sprayApp.repository.repository.nozzle

import com.gyorgyzoltan.sprayApp.model.nozzle.Nozzle
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleType

interface NozzleRepository {

    suspend fun getNozzleTypes(): List<NozzleType>

    suspend fun getNozzles(): List<Nozzle>
}
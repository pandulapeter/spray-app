package com.gyorgyzoltan.sprayApp.local

import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleType

interface NozzleTypeLocalSource {

    suspend fun getNozzleTypes(): List<NozzleType>

    fun saveNozzleTypes(nozzleTypes: List<NozzleType>)
}
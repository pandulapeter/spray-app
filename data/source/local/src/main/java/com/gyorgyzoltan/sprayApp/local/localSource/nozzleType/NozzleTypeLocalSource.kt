package com.gyorgyzoltan.sprayApp.local.localSource.nozzleType

import com.gyorgyzoltan.sprayApp.local.model.NozzleTypeEntity

interface NozzleTypeLocalSource {

    suspend fun getNozzleTypes(): List<NozzleTypeEntity>
}
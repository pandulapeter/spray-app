package com.gyorgyzoltan.sprayApp.local.localSource.nozzle

import com.gyorgyzoltan.sprayApp.local.model.NozzleEntity

interface NozzleLocalSource {

    suspend fun getNozzles(): List<NozzleEntity>
}
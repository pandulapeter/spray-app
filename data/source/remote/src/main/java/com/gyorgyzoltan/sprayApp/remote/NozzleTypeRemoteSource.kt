package com.gyorgyzoltan.sprayApp.remote

import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleType

interface NozzleTypeRemoteSource {

    suspend fun getNozzleTypes(): List<NozzleType>
}
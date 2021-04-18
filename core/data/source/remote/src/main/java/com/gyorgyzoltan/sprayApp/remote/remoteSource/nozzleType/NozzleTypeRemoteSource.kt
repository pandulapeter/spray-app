package com.gyorgyzoltan.sprayApp.remote.remoteSource.nozzleType

import com.gyorgyzoltan.sprayApp.remote.model.NozzleTypeResponse

interface NozzleTypeRemoteSource {

    suspend fun getNozzleTypes(): List<NozzleTypeResponse>
}
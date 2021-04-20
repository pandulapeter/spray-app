package com.gyorgyzoltan.sprayApp.remote.remoteSource.nozzle

import com.gyorgyzoltan.sprayApp.remote.model.NozzleResponse

interface NozzleRemoteSource {

    suspend fun getNozzles() : List<NozzleResponse>
}
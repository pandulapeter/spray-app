package com.gyorgyzoltan.sprayApp.remote

import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleStub

interface NozzleStubRemoteSource {

    suspend fun getNozzleStubs(): List<NozzleStub>
}
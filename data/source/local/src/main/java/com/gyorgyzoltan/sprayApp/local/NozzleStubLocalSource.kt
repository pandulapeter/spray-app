package com.gyorgyzoltan.sprayApp.local

import com.gyorgyzoltan.sprayApp.model.nozzle.Nozzle
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleStub

interface NozzleStubLocalSource {

    suspend fun getNozzleStubs(): List<NozzleStub>

    fun saveNozzleStubs(nozzles: List<Nozzle>)
}
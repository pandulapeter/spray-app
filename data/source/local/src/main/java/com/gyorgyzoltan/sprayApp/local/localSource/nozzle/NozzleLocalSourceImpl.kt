package com.gyorgyzoltan.sprayApp.local.localSource.nozzle

import com.gyorgyzoltan.sprayApp.local.model.NozzleEntity

internal class NozzleLocalSourceImpl : NozzleLocalSource {

    override suspend fun getNozzles(): List<NozzleEntity> = emptyList() // TODO
}
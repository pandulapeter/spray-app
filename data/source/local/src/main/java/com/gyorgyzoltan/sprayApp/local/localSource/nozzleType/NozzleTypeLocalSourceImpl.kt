package com.gyorgyzoltan.sprayApp.local.localSource.nozzleType

import com.gyorgyzoltan.sprayApp.local.model.NozzleTypeEntity

internal class NozzleTypeLocalSourceImpl : NozzleTypeLocalSource {

    override suspend fun getNozzleTypes(): List<NozzleTypeEntity> = emptyList() // TODO

    override fun saveNozzleTypes(nozzleTypes: List<NozzleTypeEntity>) = Unit // TODO
}
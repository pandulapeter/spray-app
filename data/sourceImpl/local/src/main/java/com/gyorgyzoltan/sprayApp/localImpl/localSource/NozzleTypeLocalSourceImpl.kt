package com.gyorgyzoltan.sprayApp.localImpl.localSource

import com.gyorgyzoltan.sprayApp.local.NozzleTypeLocalSource
import com.gyorgyzoltan.sprayApp.localImpl.implementation.dao.NozzleTypeDao
import com.gyorgyzoltan.sprayApp.localImpl.utilities.toNozzleTypeEntity
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleType
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

internal class NozzleTypeLocalSourceImpl(
    private val nozzleTypeDao: NozzleTypeDao
) : NozzleTypeLocalSource {

    override suspend fun getNozzleTypes() = nozzleTypeDao.getAll()

    override fun saveNozzleTypes(nozzleTypes: List<NozzleType>) {
        GlobalScope.launch { nozzleTypeDao.updateAll(nozzleTypes.map { it.toNozzleTypeEntity() }) }
    }
}
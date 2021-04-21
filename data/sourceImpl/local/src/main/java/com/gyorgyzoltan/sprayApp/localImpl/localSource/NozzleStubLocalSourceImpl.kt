package com.gyorgyzoltan.sprayApp.localImpl.localSource

import com.gyorgyzoltan.sprayApp.local.NozzleStubLocalSource
import com.gyorgyzoltan.sprayApp.localImpl.implementation.dao.NozzleDao
import com.gyorgyzoltan.sprayApp.localImpl.utilities.toNozzleStubEntity
import com.gyorgyzoltan.sprayApp.model.nozzle.Nozzle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

internal class NozzleStubLocalSourceImpl(
    private val nozzleDao: NozzleDao
) : NozzleStubLocalSource {

    override suspend fun getNozzleStubs() = nozzleDao.getAll()

    override fun saveNozzleStubs(nozzles: List<Nozzle>) {
        GlobalScope.launch { nozzleDao.updateAll(nozzles.map { it.toNozzleStubEntity() }) }
    }
}
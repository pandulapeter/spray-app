package com.gyorgyzoltan.sprayApp.localImpl.localSource

import com.gyorgyzoltan.sprayApp.local.NozzleColorLocalSource
import com.gyorgyzoltan.sprayApp.localImpl.implementation.dao.NozzleColorDao

internal class NozzleColorLocalSourceImpl(
    private val nozzleColorDao: NozzleColorDao
) : NozzleColorLocalSource {

    override fun getNozzleColors() = nozzleColorDao.getAll()
}
package com.gyorgyzoltan.sprayApp.local.localSource.nozzle

import com.gyorgyzoltan.sprayApp.local.implementation.database.DatabaseManager
import com.gyorgyzoltan.sprayApp.local.model.NozzleEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

internal class NozzleLocalSourceImpl(
    private val databaseManager: DatabaseManager
) : NozzleLocalSource {

    override suspend fun getNozzles() = databaseManager.getNozzleDao().getAll()

    override fun saveNozzles(nozzles: List<NozzleEntity>) {
        GlobalScope.launch { databaseManager.getNozzleDao().updateAll(nozzles) }
    }
}
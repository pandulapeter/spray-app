package com.gyorgyzoltan.sprayApp.local.localSource.nozzleType

import com.gyorgyzoltan.sprayApp.local.implementation.database.DatabaseManager
import com.gyorgyzoltan.sprayApp.local.model.NozzleTypeEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

internal class NozzleTypeLocalSourceImpl(
    private val databaseManager: DatabaseManager
) : NozzleTypeLocalSource {

    override suspend fun getNozzleTypes() = databaseManager.getNozzleTypeDao().getAll()

    override fun saveNozzleTypes(nozzleTypes: List<NozzleTypeEntity>) {
        GlobalScope.launch { databaseManager.getNozzleTypeDao().updateAll(nozzleTypes) }
    }
}
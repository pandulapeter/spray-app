package com.gyorgyzoltan.sprayApp.local.implementation.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.gyorgyzoltan.sprayApp.local.model.NozzleEntity

@Dao
internal interface NozzleDao {

    @Query("SELECT * FROM ${NozzleEntity.TABLE_NAME}")
    suspend fun getAll(): List<NozzleEntity>

    @Query("DELETE FROM ${NozzleEntity.TABLE_NAME}")
    suspend fun deleteAll()

    @Transaction
    suspend fun updateAll(nozzles: List<NozzleEntity>) {
        deleteAll()
        insertAll(nozzles)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(nozzles: List<NozzleEntity>)
}
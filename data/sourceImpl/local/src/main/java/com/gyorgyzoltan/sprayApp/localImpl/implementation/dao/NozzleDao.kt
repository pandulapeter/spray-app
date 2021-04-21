package com.gyorgyzoltan.sprayApp.localImpl.implementation.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.gyorgyzoltan.sprayApp.localImpl.model.NozzleStubEntity

@Dao
internal interface NozzleDao {

    @Query("SELECT * FROM ${NozzleStubEntity.TABLE_NAME}")
    suspend fun getAll(): List<NozzleStubEntity>

    @Query("DELETE FROM ${NozzleStubEntity.TABLE_NAME}")
    suspend fun deleteAll()

    @Transaction
    suspend fun updateAll(nozzleStubs: List<NozzleStubEntity>) {
        deleteAll()
        insertAll(nozzleStubs)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(nozzleStubs: List<NozzleStubEntity>)
}
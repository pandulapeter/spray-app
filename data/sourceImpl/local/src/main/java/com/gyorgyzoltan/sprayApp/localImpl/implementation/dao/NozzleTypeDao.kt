package com.gyorgyzoltan.sprayApp.localImpl.implementation.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.gyorgyzoltan.sprayApp.localImpl.model.NozzleTypeEntity

@Dao
internal interface NozzleTypeDao {

    @Query("SELECT * FROM ${NozzleTypeEntity.TABLE_NAME}")
    suspend fun getAll(): List<NozzleTypeEntity>

    @Query("DELETE FROM ${NozzleTypeEntity.TABLE_NAME}")
    suspend fun deleteAll()

    @Transaction
    suspend fun updateAll(nozzleTypes: List<NozzleTypeEntity>) {
        deleteAll()
        insertAll(nozzleTypes)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(nozzleTypes: List<NozzleTypeEntity>)
}
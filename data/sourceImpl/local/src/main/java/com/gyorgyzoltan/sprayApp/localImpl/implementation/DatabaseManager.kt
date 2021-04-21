package com.gyorgyzoltan.sprayApp.localImpl.implementation

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gyorgyzoltan.sprayApp.localImpl.model.NozzleStubEntity
import com.gyorgyzoltan.sprayApp.localImpl.model.NozzleTypeEntity
import com.gyorgyzoltan.sprayApp.localImpl.implementation.dao.NozzleDao
import com.gyorgyzoltan.sprayApp.localImpl.implementation.dao.NozzleTypeDao

@Database(entities = [NozzleTypeEntity::class, NozzleStubEntity::class], version = 1)
internal abstract class DatabaseManager : RoomDatabase() {

    abstract fun getNozzleTypeDao(): NozzleTypeDao

    abstract fun getNozzleDao(): NozzleDao
}
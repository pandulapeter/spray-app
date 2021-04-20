package com.gyorgyzoltan.sprayApp.local.implementation.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gyorgyzoltan.sprayApp.local.model.NozzleEntity
import com.gyorgyzoltan.sprayApp.local.model.NozzleTypeEntity

@Database(entities = [NozzleTypeEntity::class, NozzleEntity::class], version = 1)
internal abstract class DatabaseManager : RoomDatabase() {

    abstract fun getNozzleTypeDao(): NozzleTypeDao

    abstract fun getNozzleDao(): NozzleDao
}
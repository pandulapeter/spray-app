package com.gyorgyzoltan.sprayApp.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = NozzleTypeEntity.TABLE_NAME)
data class NozzleTypeEntity(
    @PrimaryKey val name: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String
) {

    companion object {
        internal const val TABLE_NAME = "nozzleType"
    }
}
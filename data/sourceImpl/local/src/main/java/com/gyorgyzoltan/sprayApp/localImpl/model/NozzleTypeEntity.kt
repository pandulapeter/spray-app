package com.gyorgyzoltan.sprayApp.localImpl.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleType

@Entity(tableName = NozzleTypeEntity.TABLE_NAME)
internal data class NozzleTypeEntity(
    @PrimaryKey override val name: String,
    @ColumnInfo(name = "imageUrl") override val imageUrl: String
) : NozzleType {

    companion object {
        internal const val TABLE_NAME = "nozzleType"
    }
}
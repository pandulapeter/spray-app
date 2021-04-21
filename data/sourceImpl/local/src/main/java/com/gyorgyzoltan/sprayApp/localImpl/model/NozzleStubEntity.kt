package com.gyorgyzoltan.sprayApp.localImpl.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleStub

@Entity(tableName = NozzleStubEntity.TABLE_NAME)
internal data class NozzleStubEntity(
    @PrimaryKey override val name: String,
    @ColumnInfo(name = "type") override val type: String,
    @ColumnInfo(name = "color") override val color: String,
    @ColumnInfo(name = "debitAt1Bar") override val debitAt1Bar: Float,
    @ColumnInfo(name = "debitAt2Bar") override val debitAt2Bar: Float,
    @ColumnInfo(name = "debitAt3Bar") override val debitAt3Bar: Float,
    @ColumnInfo(name = "debitAt4Bar") override val debitAt4Bar: Float,
    @ColumnInfo(name = "debitAt5Bar") override val debitAt5Bar: Float,
    @ColumnInfo(name = "debitAt6Bar") override val debitAt6Bar: Float,
    @ColumnInfo(name = "debitAt7Bar") override val debitAt7Bar: Float,
    @ColumnInfo(name = "debitAt8Bar") override val debitAt8Bar: Float
) : NozzleStub {

    companion object {
        internal const val TABLE_NAME = "nozzle"
    }
}
package com.gyorgyzoltan.sprayApp.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = NozzleEntity.TABLE_NAME)
data class NozzleEntity(
    @PrimaryKey val name: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "color") val color: String,
    @ColumnInfo(name = "debitAt1Bar") val debitAt1Bar: Float,
    @ColumnInfo(name = "debitAt2Bar") val debitAt2Bar: Float,
    @ColumnInfo(name = "debitAt3Bar") val debitAt3Bar: Float,
    @ColumnInfo(name = "debitAt4Bar") val debitAt4Bar: Float,
    @ColumnInfo(name = "debitAt5Bar") val debitAt5Bar: Float,
    @ColumnInfo(name = "debitAt6Bar") val debitAt6Bar: Float,
    @ColumnInfo(name = "debitAt7Bar") val debitAt7Bar: Float,
    @ColumnInfo(name = "debitAt8Bar") val debitAt8Bar: Float
) {

    companion object {
        internal const val TABLE_NAME = "nozzle"
    }
}
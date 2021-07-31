package com.gyorgyzoltan.sprayApp.model.nozzle

interface Nozzle {

    val name: String
    val type: NozzleType
    val color: NozzleColor
    val debitAt1Bar: Float
    val debitAt2Bar: Float
    val debitAt3Bar: Float
    val debitAt4Bar: Float
    val debitAt5Bar: Float
    val debitAt6Bar: Float
    val debitAt7Bar: Float
    val debitAt8Bar: Float
}
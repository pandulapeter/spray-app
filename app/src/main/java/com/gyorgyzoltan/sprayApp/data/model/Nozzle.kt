package com.gyorgyzoltan.sprayApp.data.model

data class Nozzle(
    val name: String,
    val image: Int,
    val type: NozzleType,
    // Debit values at X bar pressure
    val debitAt1: Float?,
    val debitAt1AndHalf: Float?,
    val debitAt2: Float?,
    val debitAt2AndHalf: Float?,
    val debitAt3: Float?,
    val debitAt3AndHalf: Float?,
    val debitAt4: Float?,
    val debitAt4AndHalf: Float?,
    val debitAt5: Float?,
    val debitAt5AndHalf: Float?,
    val debitAt6: Float?,
    val debitAt6AndHalf: Float?,
    val debitAt7: Float?,
    val debitAt7AndHalf: Float?,
    val debitAt8: Float?
)
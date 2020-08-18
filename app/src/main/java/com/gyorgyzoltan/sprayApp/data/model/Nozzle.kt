package com.gyorgyzoltan.sprayApp.data.model

enum class Nozzle(
    val formattedName: String,
    val image: Int,
    val type: NozzleType,
    // Debit values at X bar pressure
    val debitAt1: Float? = null,
    val debitAt1AndHalf: Float? = null,
    val debitAt2: Float? = null,
    val debitAt2AndHalf: Float? = null,
    val debitAt3: Float? = null,
    val debitAt3AndHalf: Float? = null,
    val debitAt4: Float? = null,
    val debitAt4AndHalf: Float? = null,
    val debitAt5: Float? = null,
    val debitAt5AndHalf: Float? = null,
    val debitAt6: Float? = null,
    val debitAt6AndHalf: Float? = null,
    val debitAt7: Float? = null,
    val debitAt7AndHalf: Float? = null,
    val debitAt8: Float? = null
) {

    TEST_NOZZLE_1(
        formattedName = "Test nozzle 1",
        image = 0,
        type = NozzleType.TYPE_01
    ),
    TEST_NOZZLE_2(
        formattedName = "Test nozzle 2",
        image = 0,
        type = NozzleType.TYPE_02
    ),
    TEST_NOZZLE_3(
        formattedName = "Test nozzle 3",
        image = 0,
        type = NozzleType.TYPE_01
    )
}
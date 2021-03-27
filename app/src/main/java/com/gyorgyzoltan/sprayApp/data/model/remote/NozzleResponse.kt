package com.gyorgyzoltan.sprayApp.data.model.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NozzleResponse(
    @Json(name = KEY_NAME) val name: String? = null,
    @Json(name = TYPE) val type: String? = null,
    @Json(name = COLOR) val color: String? = null,
    @Json(name = DEBIT_AT_1_BAR) val debitAt1Bar: Float? = null,
    @Json(name = DEBIT_AT_2_BAR) val debitAt2Bar: Float? = null,
    @Json(name = DEBIT_AT_3_BAR) val debitAt3Bar: Float? = null,
    @Json(name = DEBIT_AT_4_BAR) val debitAt4Bar: Float? = null,
    @Json(name = DEBIT_AT_5_BAR) val debitAt5Bar: Float? = null,
    @Json(name = DEBIT_AT_6_BAR) val debitAt6Bar: Float? = null,
    @Json(name = DEBIT_AT_7_BAR) val debitAt7Bar: Float? = null,
    @Json(name = DEBIT_AT_8_BAR) val debitAt8Bar: Float? = null,
) {

    companion object {
        const val SHEET_NAME = "nozzles"
        const val KEY_NAME = "name"
        const val TYPE = "type"
        const val COLOR = "color"
        const val DEBIT_AT_1_BAR = "debitAt1Bar"
        const val DEBIT_AT_2_BAR = "debitAt2Bar"
        const val DEBIT_AT_3_BAR = "debitAt3Bar"
        const val DEBIT_AT_4_BAR = "debitAt4Bar"
        const val DEBIT_AT_5_BAR = "debitAt5Bar"
        const val DEBIT_AT_6_BAR = "debitAt6Bar"
        const val DEBIT_AT_7_BAR = "debitAt7Bar"
        const val DEBIT_AT_8_BAR = "debitAt8Bar"
    }
}
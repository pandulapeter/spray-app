package com.gyorgyzoltan.sprayApp.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.theapache64.retrosheet.RetrosheetInterceptor

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
    @Json(name = DEBIT_AT_8_BAR) val debitAt8Bar: Float? = null
) {

    companion object {
        internal const val SHEET_NAME = "nozzles"
        private const val KEY_NAME = "name"
        private const val TYPE = "type"
        private const val COLOR = "color"
        private const val DEBIT_AT_1_BAR = "debitAt1Bar"
        private const val DEBIT_AT_2_BAR = "debitAt2Bar"
        private const val DEBIT_AT_3_BAR = "debitAt3Bar"
        private const val DEBIT_AT_4_BAR = "debitAt4Bar"
        private const val DEBIT_AT_5_BAR = "debitAt5Bar"
        private const val DEBIT_AT_6_BAR = "debitAt6Bar"
        private const val DEBIT_AT_7_BAR = "debitAt7Bar"
        private const val DEBIT_AT_8_BAR = "debitAt8Bar"

        internal fun addSheet(interceptorBuilder: RetrosheetInterceptor.Builder) = interceptorBuilder.addSheet(
            SHEET_NAME,
            KEY_NAME,
            TYPE,
            COLOR,
            DEBIT_AT_1_BAR,
            DEBIT_AT_2_BAR,
            DEBIT_AT_3_BAR,
            DEBIT_AT_4_BAR,
            DEBIT_AT_5_BAR,
            DEBIT_AT_6_BAR,
            DEBIT_AT_7_BAR,
            DEBIT_AT_8_BAR
        )
    }
}
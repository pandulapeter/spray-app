package com.gyorgyzoltan.sprayApp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Nozzle(
    @Json(name = KEY_CREATED_AT) val createdAt: String?,
    @Json(name = KEY_NAME) val name: String?,
    @Json(name = KEY_IMAGE_URL) val imageUrl: String?,
    @Json(name = TYPE) val type: String?,
    // Debit values at X bar pressure
//    val debitAt1: Float? = null,
//    val debitAt1AndHalf: Float? = null,
//    val debitAt2: Float? = null,
//    val debitAt2AndHalf: Float? = null,
//    val debitAt3: Float? = null,
//    val debitAt3AndHalf: Float? = null,
//    val debitAt4: Float? = null,
//    val debitAt4AndHalf: Float? = null,
//    val debitAt5: Float? = null,
//    val debitAt5AndHalf: Float? = null,
//    val debitAt6: Float? = null,
//    val debitAt6AndHalf: Float? = null,
//    val debitAt7: Float? = null,
//    val debitAt7AndHalf: Float? = null,
//    val debitAt8: Float? = null
) {

    companion object {
        const val KEY_CREATED_AT = "createdAt"
        const val KEY_NAME = "name"
        const val KEY_IMAGE_URL = "imageUrl"
        const val TYPE = "type"
    }
}
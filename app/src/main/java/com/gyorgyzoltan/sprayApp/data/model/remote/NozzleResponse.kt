package com.gyorgyzoltan.sprayApp.data.model.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NozzleResponse(
    @Json(name = KEY_NAME) val name: String? = null,
    @Json(name = TYPE) val type: String? = null,
    @Json(name = COLOR) val color: String? = null
) {

    companion object {
        const val SHEET_NAME = "nozzles"
        const val KEY_NAME = "name"
        const val COLOR = "color"
        const val TYPE = "type"
    }
}
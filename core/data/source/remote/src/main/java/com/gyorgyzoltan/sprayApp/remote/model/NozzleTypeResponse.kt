package com.gyorgyzoltan.sprayApp.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NozzleTypeResponse(
    @Json(name = KEY_NAME) val name: String? = null,
    @Json(name = KEY_IMAGE_URL) val imageUrl: String? = null
) {

    companion object {
        internal const val SHEET_NAME = "types"
        internal const val KEY_NAME = "name"
        internal const val KEY_IMAGE_URL = "imageUrl"
    }
}
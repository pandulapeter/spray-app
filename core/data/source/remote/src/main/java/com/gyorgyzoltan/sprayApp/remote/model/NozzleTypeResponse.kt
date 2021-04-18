package com.gyorgyzoltan.sprayApp.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NozzleTypeResponse(
    @Json(name = KEY_NAME) val name: String? = null,
    @Json(name = KEY_IMAGE_URL) val imageUrl: String? = null
) {

    companion object {
        const val SHEET_NAME = "types"
        const val KEY_NAME = "name"
        const val KEY_IMAGE_URL = "imageUrl"
    }
}
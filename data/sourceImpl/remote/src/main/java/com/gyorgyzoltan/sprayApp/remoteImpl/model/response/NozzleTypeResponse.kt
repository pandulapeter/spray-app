package com.gyorgyzoltan.sprayApp.remoteImpl.model.response

import com.gyorgyzoltan.sprayApp.remoteImpl.model.NozzleTypeImpl
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.theapache64.retrosheet.RetrosheetInterceptor

@JsonClass(generateAdapter = true)
internal data class NozzleTypeResponse(
    @Json(name = KEY_NAME) val name: String? = null,
    @Json(name = KEY_IMAGE_URL) val imageUrl: String? = null
) {

    fun toNozzleTypeImpl(): NozzleTypeImpl? = name?.let { name ->
        imageUrl?.let { imageUrl ->
            NozzleTypeImpl(
                name = name,
                imageUrl = imageUrl
            )
        }
    }

    companion object {
        internal const val SHEET_NAME = "types"
        private const val KEY_NAME = "name"
        private const val KEY_IMAGE_URL = "imageUrl"

        internal fun addSheet(interceptorBuilder: RetrosheetInterceptor.Builder) = interceptorBuilder.addSheet(
            SHEET_NAME,
            KEY_NAME,
            KEY_IMAGE_URL
        )
    }
}
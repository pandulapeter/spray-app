package com.gyorgyzoltan.sprayApp.repository.mapper

import com.gyorgyzoltan.sprayApp.local.model.NozzleTypeEntity
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleType
import com.gyorgyzoltan.sprayApp.remote.model.NozzleTypeResponse

internal fun NozzleType.toEntity() = NozzleTypeEntity(
    name = name,
    imageUrl = imageUrl
)

internal fun NozzleTypeEntity.toNozzleType() = NozzleType(
    name = name,
    imageUrl = imageUrl
)

internal fun NozzleTypeResponse.toNozzleType(): NozzleType? = name?.let { name ->
    imageUrl?.let { imageUrl ->
        NozzleType(
            name = name,
            imageUrl = imageUrl
        )
    }
}
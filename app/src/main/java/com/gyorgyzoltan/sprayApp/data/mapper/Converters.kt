package com.gyorgyzoltan.sprayApp.data.mapper

import com.gyorgyzoltan.sprayApp.data.model.domain.Nozzle
import com.gyorgyzoltan.sprayApp.data.model.domain.NozzleColor
import com.gyorgyzoltan.sprayApp.data.model.domain.NozzleType
import com.gyorgyzoltan.sprayApp.data.model.remote.NozzleResponse
import com.gyorgyzoltan.sprayApp.data.model.remote.NozzleTypeResponse

fun NozzleTypeResponse.toNozzleType(): NozzleType? = name?.let { name ->
    imageUrl?.let { imageUrl ->
        NozzleType(
            name = name,
            imageUrl = imageUrl
        )
    }
}

fun NozzleResponse.toNozzle(
    nozzleTypes: List<NozzleType>,
    nozzleColors: List<NozzleColor>
): Nozzle? = name?.let { name ->
    nozzleTypes.firstOrNull { it.name == type }?.let { type ->
        nozzleColors.firstOrNull { it.id == color }?.let { color ->
            Nozzle(
                name = name,
                type = type,
                color = color
            )
        }
    }
}
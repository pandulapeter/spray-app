package com.gyorgyzoltan.sprayApp.repository.mapper

import com.gyorgyzoltan.sprayApp.model.nozzle.Nozzle
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleColor
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleType
import com.gyorgyzoltan.sprayApp.repository.model.remote.NozzleResponse
import com.gyorgyzoltan.sprayApp.repository.model.remote.NozzleTypeResponse

internal fun NozzleTypeResponse.toNozzleType(): NozzleType? = name?.let { name ->
    imageUrl?.let { imageUrl ->
        NozzleType(
            name = name,
            imageUrl = imageUrl
        )
    }
}

internal fun NozzleResponse.toNozzle(
    nozzleTypes: List<NozzleType>,
    nozzleColors: List<NozzleColor>
): Nozzle? = name?.let { name ->
    nozzleTypes.firstOrNull { it.name == type }?.let { type ->
        nozzleColors.firstOrNull { it.id == color }?.let { color ->
            Nozzle(
                name = name,
                type = type,
                color = color,
                debitAt1Bar = debitAt1Bar ?: -1f,
                debitAt2Bar = debitAt2Bar ?: -1f,
                debitAt3Bar = debitAt3Bar ?: -1f,
                debitAt4Bar = debitAt4Bar ?: -1f,
                debitAt5Bar = debitAt5Bar ?: -1f,
                debitAt6Bar = debitAt6Bar ?: -1f,
                debitAt7Bar = debitAt7Bar ?: -1f,
                debitAt8Bar = debitAt8Bar ?: -1f
            )
        }
    }
}
package com.gyorgyzoltan.sprayApp.localImpl.utilities

import com.gyorgyzoltan.sprayApp.localImpl.model.NozzleStubEntity
import com.gyorgyzoltan.sprayApp.localImpl.model.NozzleTypeEntity
import com.gyorgyzoltan.sprayApp.model.nozzle.Nozzle
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleType

internal fun Nozzle.toNozzleStubEntity() = NozzleStubEntity(
    name = name,
    type = type.name,
    color = color.id,
    debitAt1Bar = debitAt1Bar,
    debitAt2Bar = debitAt2Bar,
    debitAt3Bar = debitAt3Bar,
    debitAt4Bar = debitAt4Bar,
    debitAt5Bar = debitAt5Bar,
    debitAt6Bar = debitAt6Bar,
    debitAt7Bar = debitAt7Bar,
    debitAt8Bar = debitAt8Bar
)

internal fun NozzleType.toNozzleTypeEntity() = NozzleTypeEntity(
    name = name,
    imageUrl = imageUrl
)
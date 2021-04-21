package com.gyorgyzoltan.sprayApp.repository.utilities

import com.gyorgyzoltan.sprayApp.model.nozzle.Nozzle
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleColor
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleStub
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleType
import com.gyorgyzoltan.sprayApp.repository.model.NozzleImpl

internal fun NozzleStub.toNozzleImpl(
    nozzleColors: List<NozzleColor>,
    nozzleTypes: List<NozzleType>
): Nozzle? = nozzleTypes.firstOrNull { it.name == type }?.let { nozzleType ->
    nozzleColors.firstOrNull { it.id == color }?.let { nozzleColor ->
        NozzleImpl(
            name = name,
            type = nozzleType,
            color = nozzleColor,
            debitAt1Bar = debitAt1Bar,
            debitAt2Bar = debitAt2Bar,
            debitAt3Bar = debitAt3Bar,
            debitAt4Bar = debitAt4Bar,
            debitAt5Bar = debitAt5Bar,
            debitAt6Bar = debitAt6Bar,
            debitAt7Bar = debitAt7Bar,
            debitAt8Bar = debitAt8Bar
        )
    }
}
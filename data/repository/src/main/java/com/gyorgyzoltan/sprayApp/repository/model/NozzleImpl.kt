package com.gyorgyzoltan.sprayApp.repository.model

import com.gyorgyzoltan.sprayApp.model.nozzle.Nozzle
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleColor
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleType

internal data class NozzleImpl(
    override val name: String,
    override val type: NozzleType,
    override val color: NozzleColor,
    override val debitAt1Bar: Float,
    override val debitAt2Bar: Float,
    override val debitAt3Bar: Float,
    override val debitAt4Bar: Float,
    override val debitAt5Bar: Float,
    override val debitAt6Bar: Float,
    override val debitAt7Bar: Float,
    override val debitAt8Bar: Float
) : Nozzle
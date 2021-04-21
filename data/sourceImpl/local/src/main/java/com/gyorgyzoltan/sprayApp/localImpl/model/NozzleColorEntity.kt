package com.gyorgyzoltan.sprayApp.localImpl.model

import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleColor

internal data class NozzleColorEntity(
    override val id: String,
    override val value: Int,
    override val isDark: Boolean
) : NozzleColor
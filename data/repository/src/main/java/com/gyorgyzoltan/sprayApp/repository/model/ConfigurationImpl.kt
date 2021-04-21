package com.gyorgyzoltan.sprayApp.repository.model

import com.gyorgyzoltan.sprayApp.model.Configuration
import com.gyorgyzoltan.sprayApp.model.nozzle.Nozzle

internal data class ConfigurationImpl(
    override val nozzle: Nozzle? = null,
    override val wheelRadius: Float? = null,
    override val screwCount: Int? = null,
    override val nozzleCount: Int? = null,
    override val nozzleDistance: Float? = null
) : Configuration
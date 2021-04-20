package com.gyorgyzoltan.sprayApp.model

import com.gyorgyzoltan.sprayApp.model.nozzle.Nozzle

data class Configuration(
    val nozzle: Nozzle? = null,
    val wheelRadius: Float? = null,
    val screwCount: Int? = null,
    val nozzleCount: Int? = null,
    val nozzleDistance: Float? = null
) {
    val isValid = nozzle != null && wheelRadius != null && screwCount != null && nozzleCount != null && nozzleDistance != null
}
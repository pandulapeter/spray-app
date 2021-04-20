package com.gyorgyzoltan.sprayApp.model

import com.gyorgyzoltan.sprayApp.model.nozzle.Nozzle

data class Configuration(
    val nozzle: Nozzle? = null,
    val wheelRadius: Float? = null, // In cm
    val screwCount: Int? = null,
    val nozzleCount: Int? = null, // min 1, max 64
    val nozzleDistance: Float? = null // In m
) {
    val isValid = nozzle != null && wheelRadius != null && screwCount != null && nozzleCount != null && nozzleDistance != null
}
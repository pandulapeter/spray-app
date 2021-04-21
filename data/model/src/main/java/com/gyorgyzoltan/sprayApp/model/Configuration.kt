package com.gyorgyzoltan.sprayApp.model

import com.gyorgyzoltan.sprayApp.model.nozzle.Nozzle

interface Configuration {

    val nozzle: Nozzle?
    val wheelRadius: Float?
    val screwCount: Int?
    val nozzleCount: Int?
    val nozzleDistance: Float?
    val isValid get() = nozzle != null && wheelRadius != null && screwCount != null && nozzleCount != null && nozzleDistance != null
}
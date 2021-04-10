package com.gyorgyzoltan.sprayApp.model

import com.gyorgyzoltan.sprayApp.model.nozzle.Nozzle

data class Configuration(
    val nozzle: Nozzle,
    val wheelRadius: Float, // In cm
    val screwCount: Int,
    val nozzleCount: Int, // min 1, max 64
    val nozzleDistance: Float // In m
)
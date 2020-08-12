package com.gyorgyzoltan.sprayApp.data.model

data class Configuration(
    val nozzle: Nozzle,
    val wheelRadius: Float, // In cm
    val screwCount: Int,
    val nozzleCount: Int,
    val nozzleDistance: Float // In m
)
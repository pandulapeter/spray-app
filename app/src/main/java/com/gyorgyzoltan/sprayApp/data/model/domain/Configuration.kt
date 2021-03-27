package com.gyorgyzoltan.sprayApp.data.model.domain

import com.gyorgyzoltan.sprayApp.data.model.remote.NozzleResponse

data class Configuration(
    val nozzle: NozzleResponse,
    val wheelRadius: Float, // In cm
    val screwCount: Int,
    val nozzleCount: Int,
    val nozzleDistance: Float // In m
)
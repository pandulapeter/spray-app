package com.gyorgyzoltan.sprayApp.data.model.domain

import com.gyorgyzoltan.sprayApp.data.model.remote.NozzleResponse

data class Configuration(
    val nozzle: NozzleResponse,
    val wheelRadius: Float, // In cm
    val screwCount: Int,
    val nozzleCount: Int, // min 1, max 64
    val nozzleDistance: Float // In m
)
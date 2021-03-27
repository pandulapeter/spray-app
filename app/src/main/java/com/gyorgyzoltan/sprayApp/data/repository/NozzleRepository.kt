package com.gyorgyzoltan.sprayApp.data.repository

import com.gyorgyzoltan.sprayApp.data.mapper.toNozzle
import com.gyorgyzoltan.sprayApp.data.mapper.toNozzleType
import com.gyorgyzoltan.sprayApp.data.model.domain.Nozzle
import com.gyorgyzoltan.sprayApp.data.model.domain.NozzleColor
import com.gyorgyzoltan.sprayApp.data.networking.NetworkingManager

class NozzleRepository(private val networkingManager: NetworkingManager) {

    suspend fun getNozzles(): List<Nozzle> = try {
        val nozzleTypes = networkingManager.networkingService.getNozzleTypes().mapNotNull { it.toNozzleType() }
        val nozzleColors = NozzleColor.values().toList()
        networkingManager.networkingService.getNozzles().mapNotNull {
            it.toNozzle(
                nozzleTypes = nozzleTypes,
                nozzleColors = nozzleColors
            )
        }
    } catch (e: Exception) {
        emptyList()
    }
}
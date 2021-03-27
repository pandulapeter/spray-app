package com.gyorgyzoltan.sprayApp.data.repository

import com.gyorgyzoltan.sprayApp.data.mapper.toNozzle
import com.gyorgyzoltan.sprayApp.data.mapper.toNozzleType
import com.gyorgyzoltan.sprayApp.data.model.domain.Nozzle
import com.gyorgyzoltan.sprayApp.data.model.domain.NozzleColor
import com.gyorgyzoltan.sprayApp.data.model.domain.NozzleType
import com.gyorgyzoltan.sprayApp.data.networking.NetworkingManager

class NozzleRepository(private val networkingManager: NetworkingManager) {

    private var nozzleTypes: List<NozzleType>? = null

    suspend fun getNozzleTypes(): List<NozzleType> = nozzleTypes ?: try {
        networkingManager.networkingService.getNozzleTypes().mapNotNull { it.toNozzleType() }.also {
            nozzleTypes = it
        }
    } catch (e: Exception) {
        emptyList()
    }

    suspend fun getNozzles(): List<Nozzle> = try {
        val nozzleTypes = getNozzleTypes()
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
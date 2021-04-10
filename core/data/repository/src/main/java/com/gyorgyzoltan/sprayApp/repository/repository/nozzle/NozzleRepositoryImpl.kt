package com.gyorgyzoltan.sprayApp.repository.repository.nozzle

import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleColor
import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleType
import com.gyorgyzoltan.sprayApp.repository.mapper.toNozzle
import com.gyorgyzoltan.sprayApp.repository.mapper.toNozzleType
import com.gyorgyzoltan.sprayApp.repository.networking.NetworkingManager

internal class NozzleRepositoryImpl(private val networkingManager: NetworkingManager) : NozzleRepository {

    private var nozzleTypes: List<NozzleType>? = null

    override suspend fun getNozzleTypes() = nozzleTypes ?: try {
        networkingManager.networkingService.getNozzleTypes().mapNotNull { it.toNozzleType() }.also {
            nozzleTypes = it
        }
    } catch (e: Exception) {
        emptyList()
    }

    override suspend fun getNozzles() = try {
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
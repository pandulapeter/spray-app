package com.gyorgyzoltan.sprayApp.data.repository

import com.gyorgyzoltan.sprayApp.data.model.Nozzle
import com.gyorgyzoltan.sprayApp.data.networking.NetworkingManager

class NozzleRepository(private val networkingManager: NetworkingManager) {

    suspend fun getNozzles(): List<Nozzle> = try {
        networkingManager.nozzleService.getNozzles()
    } catch (e: Exception) {
        e.message
        emptyList()
    }
}
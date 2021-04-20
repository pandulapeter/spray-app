package com.gyorgyzoltan.sprayApp.repository.repository.configuration

import com.gyorgyzoltan.sprayApp.model.Configuration
import com.gyorgyzoltan.sprayApp.model.DataState
import com.gyorgyzoltan.sprayApp.model.nozzle.Nozzle
import kotlinx.coroutines.flow.Flow

interface ConfigurationRepository {

    val currentConfiguration: Flow<DataState<Configuration>>
    val isConfigurationSet: Boolean

    suspend fun refresh(isForceRefresh: Boolean)

    fun setNozzle(nozzle: Nozzle)

    fun setWheelRadius(wheelRadius: Float)

    fun setScrewCount(screwCount: Int)

    fun setNozzleCount(nozzleCount: Int)

    fun setNozzleDistance(nozzleDistance: Float)
}
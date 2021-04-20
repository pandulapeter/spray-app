package com.gyorgyzoltan.sprayApp.domain.configuration

import com.gyorgyzoltan.sprayApp.repository.repository.configuration.ConfigurationRepository

class SetNozzleDistanceUseCase(
    private val configurationRepository: ConfigurationRepository
) {
    operator fun invoke(nozzleDistance: Float) = configurationRepository.setNozzleDistance(nozzleDistance)
}
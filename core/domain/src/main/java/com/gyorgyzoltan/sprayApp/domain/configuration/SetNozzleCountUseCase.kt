package com.gyorgyzoltan.sprayApp.domain.configuration

import com.gyorgyzoltan.sprayApp.repository.repository.configuration.ConfigurationRepository

class SetNozzleCountUseCase(
    private val configurationRepository: ConfigurationRepository
) {

    operator fun invoke(nozzleCount: Int) = configurationRepository.setNozzleCount(nozzleCount)
}
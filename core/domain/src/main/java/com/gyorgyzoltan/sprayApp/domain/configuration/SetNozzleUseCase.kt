package com.gyorgyzoltan.sprayApp.domain.configuration

import com.gyorgyzoltan.sprayApp.model.nozzle.Nozzle
import com.gyorgyzoltan.sprayApp.repository.repository.configuration.ConfigurationRepository

class SetNozzleUseCase(
    private val configurationRepository: ConfigurationRepository
) {

    operator fun invoke(nozzle: Nozzle) = configurationRepository.setNozzle(nozzle)
}
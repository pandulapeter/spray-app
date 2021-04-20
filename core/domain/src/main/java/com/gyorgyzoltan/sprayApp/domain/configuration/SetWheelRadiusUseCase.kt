package com.gyorgyzoltan.sprayApp.domain.configuration

import com.gyorgyzoltan.sprayApp.repository.repository.configuration.ConfigurationRepository

class SetWheelRadiusUseCase(
    private val configurationRepository: ConfigurationRepository
) {

    operator fun invoke(wheelRadius: Float) = configurationRepository.setWheelRadius(wheelRadius)
}
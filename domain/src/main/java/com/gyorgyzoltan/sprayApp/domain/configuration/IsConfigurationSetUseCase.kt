package com.gyorgyzoltan.sprayApp.domain.configuration

import com.gyorgyzoltan.sprayApp.repository.repository.configuration.ConfigurationRepository

class IsConfigurationSetUseCase(
    private val configurationRepository: ConfigurationRepository
) {
    operator fun invoke() = configurationRepository.isConfigurationSet
}
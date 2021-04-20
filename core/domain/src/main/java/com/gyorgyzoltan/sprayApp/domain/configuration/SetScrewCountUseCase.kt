package com.gyorgyzoltan.sprayApp.domain.configuration

import com.gyorgyzoltan.sprayApp.repository.repository.configuration.ConfigurationRepository

class SetScrewCountUseCase(
    private val configurationRepository: ConfigurationRepository
) {

    operator fun invoke(screwCount: Int) = configurationRepository.setScrewCount(screwCount)
}
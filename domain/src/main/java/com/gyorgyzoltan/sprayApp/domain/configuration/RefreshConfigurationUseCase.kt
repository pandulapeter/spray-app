package com.gyorgyzoltan.sprayApp.domain.configuration

import com.gyorgyzoltan.sprayApp.repository.repository.configuration.ConfigurationRepository

class RefreshConfigurationUseCase(
    private val configurationRepository: ConfigurationRepository
) {
    suspend operator fun invoke(isForceRefresh: Boolean) = configurationRepository.refresh(isForceRefresh)
}
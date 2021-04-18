package com.gyorgyzoltan.sprayApp.domain.configuration

import com.gyorgyzoltan.sprayApp.repository.preferences.PreferenceManager

class IsConfigurationSetUseCase(
    private val preferenceManager: PreferenceManager
) {

    operator fun invoke() = preferenceManager.isConfigurationSet
}
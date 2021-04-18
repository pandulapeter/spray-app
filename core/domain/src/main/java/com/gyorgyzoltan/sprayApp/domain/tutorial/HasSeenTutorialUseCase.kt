package com.gyorgyzoltan.sprayApp.domain.tutorial

import com.gyorgyzoltan.sprayApp.repository.preferences.PreferenceManager

class HasSeenTutorialUseCase(
    private val preferenceManager: PreferenceManager
) {

    operator fun invoke() = preferenceManager.hasSeenTutorial
}
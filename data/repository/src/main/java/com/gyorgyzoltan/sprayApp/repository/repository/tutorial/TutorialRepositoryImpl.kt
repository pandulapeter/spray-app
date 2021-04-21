package com.gyorgyzoltan.sprayApp.repository.repository.tutorial

import com.gyorgyzoltan.sprayApp.local.PreferenceManager

internal class TutorialRepositoryImpl(
    private val preferenceManager: PreferenceManager
) : TutorialRepository {

    override var hasSeenTutorial
        get() = preferenceManager.hasSeenTutorial
        set(value) {
            preferenceManager.hasSeenTutorial = value
        }
}
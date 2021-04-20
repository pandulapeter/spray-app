package com.gyorgyzoltan.sprayApp.domain.tutorial

import com.gyorgyzoltan.sprayApp.repository.repository.tutorial.TutorialRepository

class SetHasSeenTutorialUseCase(
    private val tutorialRepository: TutorialRepository
) {
    operator fun invoke() {
        tutorialRepository.hasSeenTutorial = true
    }
}
package com.gyorgyzoltan.sprayApp.domain.dependency

import com.gyorgyzoltan.sprayApp.repository.repository.dependency.DependencyRepository

class GetDependenciesUseCase(
    private val dependencyRepository: DependencyRepository
) {
    operator fun invoke() = dependencyRepository.getDependencies()
}
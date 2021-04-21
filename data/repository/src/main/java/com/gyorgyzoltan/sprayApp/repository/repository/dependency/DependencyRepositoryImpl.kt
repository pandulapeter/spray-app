package com.gyorgyzoltan.sprayApp.repository.repository.dependency

import com.gyorgyzoltan.sprayApp.local.DependencyLocalSource

internal class DependencyRepositoryImpl(
    private val dependencyLocalSource: DependencyLocalSource
) : DependencyRepository {

    override fun getDependencies() = dependencyLocalSource.geDependencies()
}
package com.gyorgyzoltan.sprayApp.localImpl.localSource

import com.gyorgyzoltan.sprayApp.local.DependencyLocalSource
import com.gyorgyzoltan.sprayApp.localImpl.implementation.dao.DependencyDao

internal class DependencyLocalSourceImpl(
    private val dependencyDao: DependencyDao
) : DependencyLocalSource {

    override fun geDependencies() = dependencyDao.getAll()
}
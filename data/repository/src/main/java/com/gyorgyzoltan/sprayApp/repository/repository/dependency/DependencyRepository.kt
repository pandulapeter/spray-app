package com.gyorgyzoltan.sprayApp.repository.repository.dependency

import com.gyorgyzoltan.sprayApp.model.Dependency

interface DependencyRepository {

    fun getDependencies(): List<Dependency>
}
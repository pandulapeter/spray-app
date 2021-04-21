package com.gyorgyzoltan.sprayApp.local

import com.gyorgyzoltan.sprayApp.model.Dependency

interface DependencyLocalSource {

    fun geDependencies(): List<Dependency>
}
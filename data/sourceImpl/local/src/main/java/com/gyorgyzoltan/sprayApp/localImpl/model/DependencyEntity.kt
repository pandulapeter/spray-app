package com.gyorgyzoltan.sprayApp.localImpl.model

import com.gyorgyzoltan.sprayApp.model.Dependency

internal data class DependencyEntity(
   override val typeResourceId: Int,
   override val title: String,
   override val copyright: String,
   override val url: String
) : Dependency
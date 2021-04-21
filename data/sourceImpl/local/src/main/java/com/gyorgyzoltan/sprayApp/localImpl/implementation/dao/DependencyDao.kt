package com.gyorgyzoltan.sprayApp.localImpl.implementation.dao

import com.gyorgyzoltan.sprayApp.localImpl.R
import com.gyorgyzoltan.sprayApp.localImpl.model.DependencyEntity

internal class DependencyDao {

    fun getAll(): List<DependencyEntity> = listOf(
        DependencyEntity(
            typeResourceId = R.string.licences_apache,
            title = "Kotlin",
            copyright = "Copyright 2010-2020 JetBrains s.r.o.",
            url = "https://github.com/JetBrains/kotlin/tree/master/license"
        ),
        DependencyEntity(
            typeResourceId = R.string.licences_apache,
            title = "Kotlin Coroutines",
            copyright = "Copyright 2010-2020 JetBrains s.r.o.",
            url = "https://github.com/JetBrains/kotlin/tree/master/license"
        ),
        DependencyEntity(
            typeResourceId = R.string.licences_apache,
            title = "AndroidX AppCompat",
            copyright = "Copyright (C) 2020 The Android Open Source Project",
            url = "https://developer.android.com/jetpack/androidx/releases/appcompat"
        ),
        DependencyEntity(
            typeResourceId = R.string.licences_apache,
            title = "AndroidX Lifecycle",
            copyright = "Copyright (C) 2020 The Android Open Source Project",
            url = "https://developer.android.com/jetpack/androidx/releases/lifecycle"
        ),
        DependencyEntity(
            typeResourceId = R.string.licences_apache,
            title = "Android Material Components",
            copyright = "Copyright (C) 2020 The Android Open Source Project",
            url = "https://github.com/material-components/material-components-android/blob/master/LICENSE"
        ),
        DependencyEntity(
            typeResourceId = R.string.licences_apache,
            title = "Constraint Layout",
            copyright = "Copyright (C) 2020 The Android Open Source Project",
            url = "https://developer.android.com/jetpack/androidx/releases/constraintlayout"
        ),
        DependencyEntity(
            typeResourceId = R.string.licences_apache,
            title = "Recycler View",
            copyright = "Copyright (C) 2020 The Android Open Source Project",
            url = "https://developer.android.com/jetpack/androidx/releases/recyclerview"
        ),
        DependencyEntity(
            typeResourceId = R.string.licences_apache,
            title = "Koin",
            copyright = "",
            url = "https://github.com/InsertKoinIO/koin",
        )
    )
}
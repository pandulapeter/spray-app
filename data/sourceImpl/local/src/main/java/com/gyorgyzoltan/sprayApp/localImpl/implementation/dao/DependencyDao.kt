package com.gyorgyzoltan.sprayApp.localImpl.implementation.dao

import com.gyorgyzoltan.sprayApp.localImpl.R
import com.gyorgyzoltan.sprayApp.localImpl.model.DependencyEntity

internal class DependencyDao {

    fun getAll(): List<DependencyEntity> = listOf(
        DependencyEntity(
            typeResourceId = R.string.licences_apache,
            title = "Kotlin",
            copyright = "Copyright 2010-2021 JetBrains s.r.o.",
            url = "https://github.com/JetBrains/kotlin/tree/master"
        ),
        DependencyEntity(
            typeResourceId = R.string.licences_apache,
            title = "Kotlin Coroutines",
            copyright = "Copyright 2010-2021 JetBrains s.r.o.",
            url = "https://github.com/JetBrains/kotlin/tree/master"
        ),
        DependencyEntity(
            typeResourceId = R.string.licences_apache,
            title = "AndroidX AppCompat",
            copyright = "Copyright (C) 2021 The Android Open Source Project",
            url = "https://developer.android.com/jetpack/androidx/releases/appcompat"
        ),
        DependencyEntity(
            typeResourceId = R.string.licences_apache,
            title = "AndroidX Lifecycle",
            copyright = "Copyright (C) 2021 The Android Open Source Project",
            url = "https://developer.android.com/jetpack/androidx/releases/lifecycle"
        ),
        DependencyEntity(
            typeResourceId = R.string.licences_apache,
            title = "Android Material Components",
            copyright = "Copyright (C) 2021 The Android Open Source Project",
            url = "https://github.com/material-components/material-components-android/blob/master"
        ),
        DependencyEntity(
            typeResourceId = R.string.licences_apache,
            title = "Constraint Layout",
            copyright = "Copyright (C) 2021 The Android Open Source Project",
            url = "https://developer.android.com/jetpack/androidx/releases/constraintlayout"
        ),
        DependencyEntity(
            typeResourceId = R.string.licences_apache,
            title = "Recycler View",
            copyright = "Copyright (C) 2021 The Android Open Source Project",
            url = "https://developer.android.com/jetpack/androidx/releases/recyclerview"
        ),
        DependencyEntity(
            typeResourceId = R.string.licences_apache,
            title = "Koin",
            copyright = "Copyright (C) 2021 Koin",
            url = "https://github.com/InsertKoinIO/koin"
        ),
        DependencyEntity(
            typeResourceId = R.string.licences_apache,
            title = "Retrosheet",
            copyright = "theapache64",
            url = "https://github.com/theapache64/retrosheet"
        ),
        DependencyEntity(
            typeResourceId = R.string.licences_apache,
            title = "Coil",
            copyright = "Copyright 2021 Coil Contributors",
            url = "https://github.com/coil-kt/coil"
        )
    )
}
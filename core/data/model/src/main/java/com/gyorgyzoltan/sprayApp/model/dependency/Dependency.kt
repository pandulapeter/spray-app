package com.gyorgyzoltan.sprayApp.model.dependency

enum class Dependency(
    val type: DependencyType,
    val title: String,
    val copyright: String? = "Unknown",
    val url: String? = null
) {
    KOTLIN(
        type = DependencyType.APACHE_V2,
        title = "Kotlin",
        copyright = "Copyright 2010-2020 JetBrains s.r.o.",
        url = "https://github.com/JetBrains/kotlin/tree/master/license"
    ),
    KOTLIN_COROUTINES(
        type = DependencyType.APACHE_V2,
        title = "Kotlin Coroutines",
        copyright = "Copyright 2010-2020 JetBrains s.r.o.",
        url = "https://github.com/JetBrains/kotlin/tree/master/license"
    ),
    ANDROID_X_APP_COMPAT(
        type = DependencyType.APACHE_V2,
        title = "AndroidX AppCompat",
        copyright = "Copyright (C) 2020 The Android Open Source Project",
        url = "https://developer.android.com/jetpack/androidx/releases/appcompat"
    ),
    ANDROID_X_LIFECYCLE(
        type = DependencyType.APACHE_V2,
        title = "AndroidX Lifecycle",
        copyright = "Copyright (C) 2020 The Android Open Source Project",
        url = "https://developer.android.com/jetpack/androidx/releases/lifecycle"
    ),
    MATERIAL_COMPONENTS(
        type = DependencyType.APACHE_V2,
        title = "Android Material Components",
        copyright = "Copyright (C) 2020 The Android Open Source Project",
        url = "https://github.com/material-components/material-components-android/blob/master/LICENSE"
    ),
    CONSTRAINT_LAYOUT(
        type = DependencyType.APACHE_V2,
        title = "Constraint Layout",
        copyright = "Copyright (C) 2020 The Android Open Source Project",
        url = "https://developer.android.com/jetpack/androidx/releases/constraintlayout"
    ),
    RECYCLER_VIEW(
        type = DependencyType.APACHE_V2,
        title = "Recycler View",
        copyright = "Copyright (C) 2020 The Android Open Source Project",
        url = "https://developer.android.com/jetpack/androidx/releases/recyclerview"
    ),
    KOIN(
        type = DependencyType.APACHE_V2,
        title = "Koin",
        url = "https://github.com/InsertKoinIO/koin"
    )
}
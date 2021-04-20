package com.gyorgyzoltan.sprayApp.local.implementation.preferences

interface PreferenceManager {

    var hasSeenTutorial: Boolean
    var nozzleName: String
    val isNozzleNameSet: Boolean
    var wheelRadius: Float
    val isWheelRadiusSet: Boolean
    var screwCount: Int
    val isScrewCountSet: Boolean
    var nozzleCount: Int
    val isNozzleCountSet: Boolean
    var nozzleDistance: Float
    val isNozzleDistanceSet: Boolean
}
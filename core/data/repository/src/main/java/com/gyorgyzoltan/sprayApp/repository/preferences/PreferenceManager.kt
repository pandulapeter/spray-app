package com.gyorgyzoltan.sprayApp.repository.preferences

interface PreferenceManager {

    var hasSeenTutorial: Boolean
    var isConfigurationSet: Boolean
    var selectedNozzleName: String
}
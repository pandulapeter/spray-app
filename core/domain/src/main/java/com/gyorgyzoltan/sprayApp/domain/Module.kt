package com.gyorgyzoltan.sprayApp.domain

import com.gyorgyzoltan.sprayApp.domain.configuration.IsConfigurationSetUseCase
import com.gyorgyzoltan.sprayApp.domain.nozzle.NozzleTypesUseCase
import com.gyorgyzoltan.sprayApp.domain.nozzle.RefreshNozzleTypesUseCase
import com.gyorgyzoltan.sprayApp.domain.tutorial.HasSeenTutorialUseCase
import com.gyorgyzoltan.sprayApp.domain.tutorial.SetHasSeenTutorialUseCase
import org.koin.dsl.module

val domainModule = module {

    // Configuration
    factory { IsConfigurationSetUseCase(get()) }

    // Nozzle
    factory { NozzleTypesUseCase(get()) }
    factory { RefreshNozzleTypesUseCase(get()) }

    // Tutorial
    factory { HasSeenTutorialUseCase(get()) }
    factory { SetHasSeenTutorialUseCase(get()) }
}
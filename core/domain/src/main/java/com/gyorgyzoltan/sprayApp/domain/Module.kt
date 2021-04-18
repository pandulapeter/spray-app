package com.gyorgyzoltan.sprayApp.domain

import com.gyorgyzoltan.sprayApp.domain.configuration.IsConfigurationSetUseCase
import com.gyorgyzoltan.sprayApp.domain.nozzle.NozzlesUseCase
import com.gyorgyzoltan.sprayApp.domain.nozzle.RefreshNozzlesUseCase
import com.gyorgyzoltan.sprayApp.domain.tutorial.HasSeenTutorialUseCase
import com.gyorgyzoltan.sprayApp.domain.tutorial.SetHasSeenTutorialUseCase
import org.koin.dsl.module

val domainModule = module {

    // Configuration
    factory { IsConfigurationSetUseCase(get()) }

    // Nozzle
    factory { NozzlesUseCase(get()) }
    factory { RefreshNozzlesUseCase(get()) }

    // Tutorial
    factory { HasSeenTutorialUseCase(get()) }
    factory { SetHasSeenTutorialUseCase(get()) }
}
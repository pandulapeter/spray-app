package com.gyorgyzoltan.sprayApp.domain

import com.gyorgyzoltan.sprayApp.domain.configuration.ConfigurationUseCase
import com.gyorgyzoltan.sprayApp.domain.configuration.IsConfigurationSetUseCase
import com.gyorgyzoltan.sprayApp.domain.configuration.RefreshConfigurationUseCase
import com.gyorgyzoltan.sprayApp.domain.configuration.SetNozzleCountUseCase
import com.gyorgyzoltan.sprayApp.domain.configuration.SetNozzleDistanceUseCase
import com.gyorgyzoltan.sprayApp.domain.configuration.SetNozzleUseCase
import com.gyorgyzoltan.sprayApp.domain.configuration.SetScrewCountUseCase
import com.gyorgyzoltan.sprayApp.domain.configuration.SetWheelRadiusUseCase
import com.gyorgyzoltan.sprayApp.domain.nozzle.NozzlesUseCase
import com.gyorgyzoltan.sprayApp.domain.nozzle.RefreshNozzlesUseCase
import com.gyorgyzoltan.sprayApp.domain.tutorial.HasSeenTutorialUseCase
import com.gyorgyzoltan.sprayApp.domain.tutorial.SetHasSeenTutorialUseCase
import org.koin.dsl.module

val domainModule = module {

    // Configuration
    factory { ConfigurationUseCase(get()) }
    factory { IsConfigurationSetUseCase(get()) }
    factory { RefreshConfigurationUseCase(get()) }
    factory { SetNozzleUseCase(get()) }
    factory { SetNozzleCountUseCase(get()) }
    factory { SetNozzleDistanceUseCase(get()) }
    factory { SetScrewCountUseCase(get()) }
    factory { SetWheelRadiusUseCase(get()) }

    // Nozzle
    factory { NozzlesUseCase(get()) }
    factory { RefreshNozzlesUseCase(get()) }

    // Tutorial
    factory { HasSeenTutorialUseCase(get()) }
    factory { SetHasSeenTutorialUseCase(get()) }
}
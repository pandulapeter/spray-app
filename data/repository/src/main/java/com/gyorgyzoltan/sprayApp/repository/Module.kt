package com.gyorgyzoltan.sprayApp.repository

import com.gyorgyzoltan.sprayApp.repository.repository.configuration.ConfigurationRepository
import com.gyorgyzoltan.sprayApp.repository.repository.configuration.ConfigurationRepositoryImpl
import com.gyorgyzoltan.sprayApp.repository.repository.dependency.DependencyRepository
import com.gyorgyzoltan.sprayApp.repository.repository.dependency.DependencyRepositoryImpl
import com.gyorgyzoltan.sprayApp.repository.repository.nozzle.NozzleRepository
import com.gyorgyzoltan.sprayApp.repository.repository.nozzle.NozzleRepositoryImpl
import com.gyorgyzoltan.sprayApp.repository.repository.nozzleType.NozzleTypeRepository
import com.gyorgyzoltan.sprayApp.repository.repository.nozzleType.NozzleTypeRepositoryImpl
import com.gyorgyzoltan.sprayApp.repository.repository.tutorial.TutorialRepository
import com.gyorgyzoltan.sprayApp.repository.repository.tutorial.TutorialRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<ConfigurationRepository> { ConfigurationRepositoryImpl(get(), get()) }
    single<DependencyRepository> { DependencyRepositoryImpl(get()) }
    single<NozzleRepository> { NozzleRepositoryImpl(get(), get(), get(), get()) }
    single<NozzleTypeRepository> { NozzleTypeRepositoryImpl(get(), get()) }
    single<TutorialRepository> { TutorialRepositoryImpl(get()) }
}
package com.gyorgyzoltan.sprayApp.repository.repository.configuration

import com.gyorgyzoltan.sprayApp.local.implementation.preferences.PreferenceManager
import com.gyorgyzoltan.sprayApp.model.Configuration
import com.gyorgyzoltan.sprayApp.model.shared.DataState
import com.gyorgyzoltan.sprayApp.model.nozzle.Nozzle
import com.gyorgyzoltan.sprayApp.repository.repository.nozzle.NozzleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged

internal class ConfigurationRepositoryImpl(
    private val preferenceManager: PreferenceManager,
    private val nozzleRepository: NozzleRepository
) : ConfigurationRepository {

    private val configuration = MutableStateFlow(Configuration())
    override val currentConfiguration = combine(
        configuration,
        nozzleRepository.nozzles
    ) { _, nozzlesDataState ->
        when (nozzlesDataState) {
            is DataState.Error -> DataState.Error(createConfiguration(), nozzlesDataState.exception)
            is DataState.Idle -> DataState.Idle(createConfiguration())
            is DataState.Loading -> DataState.Loading(createConfiguration())
        }
    }.distinctUntilChanged()
    override val isConfigurationSet get() = configuration.value.isValid || arePreferenceFieldsSet()

    override suspend fun refresh(isForceRefresh: Boolean) = nozzleRepository.refresh(isForceRefresh)

    override fun setNozzle(nozzle: Nozzle) {
        preferenceManager.nozzleName = nozzle.name
        configuration.value = createConfiguration(nozzle = nozzle)
    }

    override fun setWheelRadius(wheelRadius: Float) {
        preferenceManager.wheelRadius = wheelRadius
        configuration.value = createConfiguration(wheelRadius = wheelRadius)
    }

    override fun setScrewCount(screwCount: Int) {
        preferenceManager.screwCount = screwCount
        configuration.value = createConfiguration(screwCount = screwCount)
    }

    override fun setNozzleCount(nozzleCount: Int) {
        preferenceManager.nozzleCount = nozzleCount
        configuration.value = createConfiguration(nozzleCount = nozzleCount)
    }

    override fun setNozzleDistance(nozzleDistance: Float) {
        preferenceManager.nozzleDistance = nozzleDistance
        configuration.value = createConfiguration(nozzleDistance = nozzleDistance)
    }

    private fun createConfiguration(
        nozzle: Nozzle? = null,
        wheelRadius: Float? = null,
        screwCount: Int? = null,
        nozzleCount: Int? = null,
        nozzleDistance: Float? = null
    ) = Configuration(
        nozzle = nozzle ?: configuration.value.nozzle ?: nozzleRepository.nozzles.value.data?.firstOrNull { it.name == preferenceManager.nozzleName },
        wheelRadius = wheelRadius ?: configuration.value.wheelRadius ?: if (preferenceManager.isWheelRadiusSet) preferenceManager.wheelRadius else null,
        screwCount = screwCount ?: configuration.value.screwCount ?: if (preferenceManager.isScrewCountSet) preferenceManager.screwCount else null,
        nozzleCount = nozzleCount ?: configuration.value.nozzleCount ?: if (preferenceManager.isNozzleCountSet) preferenceManager.nozzleCount else null,
        nozzleDistance = nozzleDistance ?: configuration.value.nozzleDistance ?: if (preferenceManager.isNozzleDistanceSet) preferenceManager.nozzleDistance else null
    )

    // TODO: Nozzle name should be validated!
    private fun arePreferenceFieldsSet() = preferenceManager.isNozzleNameSet &&
            preferenceManager.isWheelRadiusSet &&
            preferenceManager.isScrewCountSet &&
            preferenceManager.isNozzleCountSet &&
            preferenceManager.isNozzleDistanceSet
}
package com.gyorgyzoltan.sprayApp.domain.nozzle

import com.gyorgyzoltan.sprayApp.repository.repository.nozzle.NozzleRepository
import com.gyorgyzoltan.sprayApp.repository.repository.nozzle.NozzleTypeRepository

class RefreshNozzlesUseCase(
    private val nozzleRepository: NozzleRepository,
    private val nozzleTypeRepository: NozzleTypeRepository
) {

    suspend operator fun invoke(isForceRefresh: Boolean) {
        if (isForceRefresh || nozzleTypeRepository.nozzleTypes.value.data == null) {
            nozzleTypeRepository.refresh(true)
        }
        nozzleRepository.refresh(isForceRefresh, nozzleTypeRepository.nozzleTypes.value.data!!)
    }
}
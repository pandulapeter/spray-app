package com.gyorgyzoltan.sprayApp.domain.nozzle

import com.gyorgyzoltan.sprayApp.repository.repository.nozzle.NozzleTypeRepository

class RefreshNozzleTypesUseCase(
    private val nozzleTypeRepository: NozzleTypeRepository
) {

    suspend operator fun invoke() = nozzleTypeRepository.refresh()
}
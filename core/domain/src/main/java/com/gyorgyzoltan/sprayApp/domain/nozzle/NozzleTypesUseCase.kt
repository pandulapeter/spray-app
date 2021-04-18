package com.gyorgyzoltan.sprayApp.domain.nozzle

import com.gyorgyzoltan.sprayApp.repository.repository.nozzle.NozzleTypeRepository

class NozzleTypesUseCase(
    private val nozzleTypeRepository: NozzleTypeRepository
) {

    operator fun invoke() = nozzleTypeRepository.nozzleTypes
}
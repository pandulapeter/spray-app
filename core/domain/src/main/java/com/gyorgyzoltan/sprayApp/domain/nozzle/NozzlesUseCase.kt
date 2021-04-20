package com.gyorgyzoltan.sprayApp.domain.nozzle

import com.gyorgyzoltan.sprayApp.repository.repository.nozzle.NozzleRepository

class NozzlesUseCase(
    private val nozzleRepository: NozzleRepository
) {
    operator fun invoke() = nozzleRepository.nozzles
}
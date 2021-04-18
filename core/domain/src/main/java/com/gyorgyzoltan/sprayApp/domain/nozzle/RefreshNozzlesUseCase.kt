package com.gyorgyzoltan.sprayApp.domain.nozzle

import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleType
import com.gyorgyzoltan.sprayApp.repository.repository.nozzle.NozzleRepository

class RefreshNozzlesUseCase(
    private val nozzleRepository: NozzleRepository
) {

    suspend operator fun invoke(nozzleTypes: List<NozzleType>) = nozzleRepository.refresh(nozzleTypes)
}
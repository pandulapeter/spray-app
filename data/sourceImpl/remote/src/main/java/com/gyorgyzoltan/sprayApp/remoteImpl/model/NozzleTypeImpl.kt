package com.gyorgyzoltan.sprayApp.remoteImpl.model

import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleType

internal data class NozzleTypeImpl(
    override val name: String,
    override val imageUrl: String
) : NozzleType
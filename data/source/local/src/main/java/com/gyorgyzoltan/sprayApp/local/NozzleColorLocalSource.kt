package com.gyorgyzoltan.sprayApp.local

import com.gyorgyzoltan.sprayApp.model.nozzle.NozzleColor

interface NozzleColorLocalSource {

    fun getNozzleColors(): List<NozzleColor>
}
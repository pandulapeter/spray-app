package com.gyorgyzoltan.sprayApp.data.model

import androidx.annotation.ColorRes
import com.gyorgyzoltan.sprayApp.R

enum class NozzleType(
    @ColorRes val color: Int,
    val diameter: String
) {

    TYPE_01(
        color = R.color.nozzle_type_orange,
        diameter = "01"
    ),
    TYPE_02(
        color = R.color.nozzle_type_green,
        diameter = "015"
    ),
    TYPE_03(
        color = R.color.nozzle_type_yellow,
        diameter = "02"
    )
}
package com.gyorgyzoltan.sprayApp.model.nozzle

import androidx.annotation.ColorRes
import com.gyorgyzoltan.sprayApp.model.R

enum class NozzleColor(
    val id: String,
    @ColorRes val value: Int,
    val isDark: Boolean
) {

    ORANGE(
        id = "orange",
        value = R.color.nozzle_type_orange,
        isDark = false // TODO
    ),
    GREEN(
        id = "green",
        value = R.color.nozzle_type_green,
        isDark = false // TODO
    ),
    YELLOW(
        id = "yellow",
        value = R.color.nozzle_type_yellow,
        isDark = false // TODO
    ),
    PURPLE(
        id = "purple",
        value = R.color.nozzle_type_purple,
        isDark = false // TODO
    ),
    DARK_BLUE(
        id = "darkBlue",
        value = R.color.nozzle_type_dark_blue,
        isDark = false // TODO
    ),
    RED(
        id = "red",
        value = R.color.nozzle_type_red,
        isDark = false // TODO
    ),
    BROWN(
        id = "brown",
        value = R.color.nozzle_type_brown,
        isDark = false // TODO
    ),
    GRAY(
        id = "gray",
        value = R.color.nozzle_type_gray,
        isDark = false // TODO
    ),
    WHITE(
        id = "white",
        value = R.color.nozzle_type_white,
        isDark = false // TODO
    ),
    LIGHT_BLUE(
        id = "lightBlue",
        value = R.color.nozzle_type_light_blue,
        isDark = false // TODO
    ),
}
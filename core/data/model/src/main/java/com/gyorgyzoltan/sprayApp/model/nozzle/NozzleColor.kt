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
        isDark = false
    ),
    GREEN(
        id = "green",
        value = R.color.nozzle_type_green,
        isDark = true
    ),
    YELLOW(
        id = "yellow",
        value = R.color.nozzle_type_yellow,
        isDark = false
    ),
    PURPLE(
        id = "purple",
        value = R.color.nozzle_type_purple,
        isDark = true
    ),
    DARK_BLUE(
        id = "darkBlue",
        value = R.color.nozzle_type_dark_blue,
        isDark = true
    ),
    RED(
        id = "red",
        value = R.color.nozzle_type_red,
        isDark = true
    ),
    BROWN(
        id = "brown",
        value = R.color.nozzle_type_brown,
        isDark = true
    ),
    GRAY(
        id = "gray",
        value = R.color.nozzle_type_gray,
        isDark = true
    ),
    WHITE(
        id = "white",
        value = R.color.nozzle_type_white,
        isDark = false
    ),
    LIGHT_BLUE(
        id = "lightBlue",
        value = R.color.nozzle_type_light_blue,
        isDark = true
    ),
}
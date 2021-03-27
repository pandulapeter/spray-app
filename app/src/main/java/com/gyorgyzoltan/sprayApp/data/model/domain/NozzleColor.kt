package com.gyorgyzoltan.sprayApp.data.model.domain

import androidx.annotation.ColorRes
import com.gyorgyzoltan.sprayApp.R

enum class NozzleColor(
    val id: String,
    @ColorRes val value: Int
) {

    ORANGE(
        id = "orange",
        value = R.color.nozzle_type_orange
    ),
    GREEN(
        id = "green",
        value = R.color.nozzle_type_green
    ),
    YELLOW(
        id = "yellow",
        value = R.color.nozzle_type_yellow
    ),
    PURPLE(
        id = "purple",
        value = R.color.nozzle_type_purple
    ),
    DARK_BLUE(
        id = "darkBlue",
        value = R.color.nozzle_type_dark_blue
    ),
    RED(
        id = "red",
        value = R.color.nozzle_type_red
    ),
    BROWN(
        id = "brown",
        value = R.color.nozzle_type_brown
    ),
    GRAY(
        id = "gray",
        value = R.color.nozzle_type_gray
    ),
    WHITE(
        id = "white",
        value = R.color.nozzle_type_white
    ),
    LIGHT_BLUE(
        id = "lightBlue",
        value = R.color.nozzle_type_light_blue
    ),
}
package com.gyorgyzoltan.sprayApp.data.model.domain

import androidx.annotation.ColorRes
import com.gyorgyzoltan.sprayApp.R

enum class NozzleColor(
    val id: String,
    @ColorRes val value: Int
) {

    ORANGE(
        id = "oragne",
        value = R.color.nozzle_type_orange
    ),
    GREEN(
        id = "green",
        value = R.color.nozzle_type_orange
    ),
    YELLOW(
        id = "yellow",
        value = R.color.nozzle_type_orange
    ),
    PURPLE(
        id = "purple",
        value = R.color.nozzle_type_orange
    ),
    DARK_BLUE(
        id = "darkBlue",
        value = R.color.nozzle_type_orange
    ),
    RED(
        id = "red",
        value = R.color.nozzle_type_orange
    ),
    BROWN(
        id = "brown",
        value = R.color.nozzle_type_orange
    ),
    GRAY(
        id = "gray",
        value = R.color.nozzle_type_orange
    ),
    WHITE(
        id = "white",
        value = R.color.nozzle_type_orange
    ),
    LIGHT_BLUE(
        id = "lightBlue",
        value = R.color.nozzle_type_orange
    ),
}
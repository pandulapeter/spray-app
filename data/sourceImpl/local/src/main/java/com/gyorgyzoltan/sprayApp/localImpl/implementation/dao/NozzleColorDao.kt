package com.gyorgyzoltan.sprayApp.localImpl.implementation.dao

import com.gyorgyzoltan.sprayApp.localImpl.R
import com.gyorgyzoltan.sprayApp.localImpl.model.NozzleColorEntity

internal class NozzleColorDao {

    fun getAll(): List<NozzleColorEntity> = listOf(
        NozzleColorEntity(
            id = "orange",
            value = R.color.nozzle_type_orange,
            isDark = false
        ),
        NozzleColorEntity(
            id = "green",
            value = R.color.nozzle_type_green,
            isDark = true
        ),
        NozzleColorEntity(
            id = "yellow",
            value = R.color.nozzle_type_yellow,
            isDark = false
        ),
        NozzleColorEntity(
            id = "purple",
            value = R.color.nozzle_type_purple,
            isDark = true
        ),
        NozzleColorEntity(
            id = "darkBlue",
            value = R.color.nozzle_type_dark_blue,
            isDark = true
        ),
        NozzleColorEntity(
            id = "red",
            value = R.color.nozzle_type_red,
            isDark = true
        ),
        NozzleColorEntity(
            id = "brown",
            value = R.color.nozzle_type_brown,
            isDark = true
        ),
        NozzleColorEntity(
            id = "gray",
            value = R.color.nozzle_type_gray,
            isDark = true
        ),
        NozzleColorEntity(
            id = "white",
            value = R.color.nozzle_type_white,
            isDark = false
        ),
        NozzleColorEntity(
            id = "lightBlue",
            value = R.color.nozzle_type_light_blue,
            isDark = true
        )
    )
}
package com.gyorgyzoltan.sprayApp.utils

import com.gyorgyzoltan.sprayApp.data.model.domain.Nozzle

object NozzleUtils {

    fun calculatePressure(debit: Float, nozzle: Nozzle): Float = when {
        debit < nozzle.debitAt1Bar -> calculatePressureBetweenTwoPoints(debit, 0f, nozzle.debitAt1Bar, 0f, 1f)
        debit >= nozzle.debitAt1Bar && debit < nozzle.debitAt2Bar -> calculatePressureBetweenTwoPoints(debit, nozzle.debitAt1Bar, nozzle.debitAt2Bar, 1f, 2f)
        debit >= nozzle.debitAt2Bar && debit < nozzle.debitAt3Bar -> calculatePressureBetweenTwoPoints(debit, nozzle.debitAt2Bar, nozzle.debitAt3Bar, 2f, 3f)
        debit >= nozzle.debitAt3Bar && debit < nozzle.debitAt4Bar -> calculatePressureBetweenTwoPoints(debit, nozzle.debitAt3Bar, nozzle.debitAt4Bar, 3f, 4f)
        debit >= nozzle.debitAt4Bar && debit < nozzle.debitAt5Bar -> calculatePressureBetweenTwoPoints(debit, nozzle.debitAt4Bar, nozzle.debitAt5Bar, 4f, 5f)
        debit >= nozzle.debitAt5Bar && debit < nozzle.debitAt6Bar -> calculatePressureBetweenTwoPoints(debit, nozzle.debitAt5Bar, nozzle.debitAt6Bar, 5f, 6f)
        debit >= nozzle.debitAt6Bar && debit < nozzle.debitAt7Bar -> calculatePressureBetweenTwoPoints(debit, nozzle.debitAt6Bar, nozzle.debitAt7Bar, 6f, 7f)
        debit >= nozzle.debitAt7Bar && debit < nozzle.debitAt8Bar -> calculatePressureBetweenTwoPoints(debit, nozzle.debitAt7Bar, nozzle.debitAt8Bar, 7f, 8f)
        else -> throw  IllegalArgumentException("")
    }

    private fun calculatePressureBetweenTwoPoints(debit: Float, debitMin: Float, debitMax: Float, pressureMin: Float, pressureMax: Float): Float =
        if (debitMin == -1f || debitMax == -1f) {
            throw  IllegalArgumentException("Out of range")
        } else {
            (pressureMin * debit - pressureMax * debit - pressureMin * debitMax + pressureMax * debitMin) / (debitMin - debitMax)
        }
}